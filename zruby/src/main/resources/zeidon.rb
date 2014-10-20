include Java

include_class 'com.quinsoft.zeidon.standardoe.JavaObjectEngine'
include_class 'com.quinsoft.zeidon.CursorPosition'
include_class 'com.quinsoft.zeidon.CursorResult'

#alias :where :lambda

module Zeidon
  @@oe = nil

  def self.get_object_engine( joe = nil )
    return @@oe || @@oe = ObjectEngine.new( joe || JavaObjectEngine.getInstance() )
  end

  class ObjectEngine
    
    def initialize joe
      @joe = joe
      @applications = {}
      @joe.getApplicationList.each do |app|
        name = app.getName.to_s
        @applications[name] = Application.new self, app
      end
    end

    def applications
      @applications.keys.sort_by{ |a| a.downcase  }
    end

    def respond_to?( id )
      @joe.respond_to?( id ) || @applications[id.to_s] || super
    end
    
    def method_missing( id, *args, &block )
      return @joe.send( id, *args, &block ) if @joe.respond_to?( id )
      return @applications[id.to_s] if @applications[id.to_s]
      super
    end

    def get_app app_name
      @applications[app_name.to_s] || raise( "Unknown application name #{app_name}" )
    end

  end # ObjectEngine
  
  class Application
    attr_reader :japp, :oe

    def initialize oe, japp
      @oe   = oe
      @japp = japp
    end
    
    def create_task
      return Task.new( self, @oe.createTask( @japp.getName ) )  
    end

    def respond_to?( id )
      @japp.respond_to?( id ) || super
    end
    
    def method_missing( id, *args, &block )
      return @japp.send( id, *args, &block ) if @japp.respond_to?( id )
      super
    end

    def get_loddef_list
      xoddir = @japp.getBinDir 
      files = Dir.glob("#{xoddir}/*.xod", File::FNM_CASEFOLD)
      if files.length == 0
        # We didn't find any?  Hmm...that's odd.  Let's try prepending ZEIDON_HOME
        # This helps us handle the case where ZEIDON.APP refers to resources in a .jar.
        files = Dir.glob("#{ENV['ZEIDON_HOME']}/#{xoddir}/*.xod", File::FNM_CASEFOLD)
      end
      return files.map{|f| File.basename(f, '.XOD')}.sort
    end

    def to_s
      return @japp.toString
    end
  end # Application
  
  class Task
    include_class "com.quinsoft.zeidon.ActivateFlags"
    attr_reader :jtask

    def initialize( app, jtask )
      @app = app
      @jtask = jtask
    end
  
    def activate view_od, argmap = {}, &block
      qual = Qualification.new( @jtask, view_od )
      qual.add_qual( argmap[:qual] )
      qual.add_options( argmap[:options] )
      yield qual if block_given?
      jview = qual.activate
      return View.new jview
    end

    def activate_empty view_od
      jview = @jtask.activateEmptyObjectInstance( view_od )
      return View.new jview
    end

    def get_view( view_name )
      jview = @jtask.getViewByName( view_name )
      return nil if jview.nil?
      return View.new jview
    end

    def respond_to?( id )
      return @jtask.respond_to?( id ) || super
    end

    def system_task
      return Task.new( @app.oe.ZeidonSystem, @jtask.getSystemTask )
    end

    def method_missing( id, *args, &block )
      return @jtask.send( id, *args, &block ) if @jtask.respond_to?( id )
      super
    end
  end # Task
  
  class View
    attr_reader :jview

    def initialize jview
      @jview = jview
      @jloddef = @jview.getLodDef
      load_cursors              
    end

    def load_cursors
      @cursors = {}
      @jloddef.getViewEntitiesHier.each do |ve|
        name = ve.getName.to_s
        @cursors[ name ] = Cursor.new( self, ve, @jview.cursor( ve.getName ) )
      end
    end
    
    def respond_to?( id )
      return @jview.respond_to?( id ) || @cursors[id.to_s] || super
    end
    
    def method_missing( id, *args, &block )
      return @jview.send( id, *args, &block ) if @jview.respond_to?( id )
      return @cursors[id.to_s] || super
    end
    
    def entities
      return @cursors.keys
    end

    def cursor entity_name
      return @cursors[entity_name]
    end

    def copy_view
      return View.new( jview.newView )
    end
  end # View
  
  class Cursor
    attr_reader :jcursor

    def initialize view, jview_entity, jcursor
      @view = view
      raise "Internal error" unless @view.kind_of? View
      @jcursor = jcursor
      
      @attributes = {}
      jview_entity.getAttributes.each do |va|
        name = va.getName.to_s
        @attributes[ name ] = Attribute.new @jcursor, name, va
      end
    end

    def get_name
      return @jcursor.getEntityDef.getName
    end

    def get_parent
      jparent = @jcursor.getParent
      return nil if jparent.nil?
      return @view.cursor( jparent.getName )
    end

    def attributes
      return @attributes.values
    end
    
    def get_attribute attr
      return @attributes[ attr ]
    end
    
    def get_key
      @attributes.each_pair do |name,attr|
        return attr if attr.is_key?
      end
    end

    def set_attribute attr, *args
      value = args[0]
      value = value.internal_value if value.kind_of?( Attribute )
      @jcursor.setAttribute( attr, value );
    end

    def include_subobject( cursor )
      @jcursor.includeSubobject( cursor.jcursor, CursorPosition::LAST )
    end

    def each( args = {} )
      if args[:scope]
        if args[:scope] == :oi
          rc = @jcursor.setFirstWithinOi
        else
          rc = @jcursor.setFirst( args[:scope] )
        end
      else
        rc = @jcursor.setFirst
      end

      while ( rc.isSet )
        yield self
        rc = @jcursor.setNextContinue
      end
    end
 
    def matching( args = {} ) # not fully functional
      matches = []
      if args[:scope]
        if args[:scope] == :oi
          rc = @jcursor.setFirstWithinOi
          @jcursor.logEntity
        else
          rc = @jcursor.setFirst( args[:scope] )
        end
      else
        rc = @jcursor.setFirst
      end

      while ( rc.isSet )
        # What do we add to matches?
        matches << yield 
        rc = @jcursor.setNextContinue
      end
      
    end

    def create( position = CursorPosition::NEXT )
      @jcursor.createEntity( position )
    end

    def each_child
      @jcursor.getEntityDef.getChildren.each do |jve|
        yield @view.cursor( jve.getName )
      end
    end

    def respond_to?( id )
      return @jcursor.respond_to?( id ) || @attributes[id.to_s] || id.to_s =~ /(.*)=/ || super
    end
    
    def method_missing( id, *args, &block )
      return @jcursor.send( id, *args, &block ) if @jcursor.respond_to?( id )
      return get_attribute( id.to_s ) if @attributes[id.to_s]
      if id.to_s =~ /(.*)=/
        name = $1
        return set_attribute( name, *args ) if @attributes[name]
      end
      super
    end
  end # Cursor
  
  class Attribute
    attr_reader :name

    def initialize jcursor, name, jAttributeDefute
      @name = name
      @jcursor = jcursor
      @jAttributeDefute = jAttributeDefute
    end

    def coerce(other)
 #     puts "In coerce other = #{other.class}"
      return case
               when other.kind_of?( Fixnum )
                   [other,self.to_i]
               when other.kind_of?( String )
                   [other,self.to_s] 
               else
                   raise "Can't convert"
             end
    end

    def set( *args )
      @jcursor.setAttribute( @name, *args )
    end

    def to_s
      @jcursor.getStringFromAttribute( @name )
    end
 
    alias :to_str :to_s # So that Ruby automatically converts an Attribute to a string.

    def to_i
      @jcursor.getIntegerFromAttribute( @name ).to_i
    end

    def to_f
      @jcursor.getDoubleFromAttribute( @name ).to_f
    end

    def internal_value
      return @jcursor.getInternalAttributeValue( @jAttributeDefute )
    end

    def entity_name
      return @jAttributeDefute.getEntityDef.getName
    end

    def operators( op, value )
#      puts "Op = #{op.to_s} value = #{value.to_s}"
      return self.to_i.send( op, value ) if value.kind_of? Fixnum
      return self.to_f.send( op, value ) if value.kind_of? Float
      return self.to_s.send( op, value.to_s )
    end

    def +( value )
      return operators( :+, value )
    end
    
    def *( value )
      return operators( :*, value )
    end
    
    def -( value )
      return operators( :-, value )
    end
    
    def /( value )
      return operators( :/, value )
    end
    
    def ==( value )
      return operators( :==, value )
    end

    def <( value )
      return operators( :<, value )
    end

    def >( value )
      return operators( :>, value )
    end

    def <=( value )
      return operators( :<=, value )
    end

    def >=( value )
      return operators( :>=, value )
    end

    def is_hidden?
      return @jAttributeDefute.isHidden
    end      

    def is_null?
      return @jcursor.isAttributeNull( @jAttributeDefute)
    end

    def respond_to?( id )
     return @jAttributeDefute.respond_to?( id ) || super
    end

    def method_missing( id, *args, &block )
      return @jAttributeDefute.send( id, *args, &block ) if @jAttributeDefute.respond_to?( id )
      return @jcursor.send( id, *args, &block ) if @jcursor.respond_to?( id )
      super
    end

  end # Attribute

  class Qualification
    include_class "com.quinsoft.zeidon.utils.QualificationBuilder"
    
    def initialize(jtask, view_od_name)
      @jqual = QualificationBuilder.new( jtask )
      @jqual.setLodDef( view_od_name )
      @entities = {}
      jloddef = @jqual.getLodDef
      jloddef.getViewEntitiesHier.each do |ve|
        name = ve.getName.to_s
        @entities[ name ] = QualEntity.new( @jqual, ve )
      end
    end
    
    # Adds qualification from a hash object
    def add_qual_hash( qual )
      return if qual.length == 0 # Empty hash.
    end

    # Adds qualification from an array object
    def add_qual_array( qual )
      return if qual.length == 0 # Empty array.

      # Check to see if we have nested arrays.  If we don't have nested arrays,
      # this should just be a simple case of qualification
      # specified as a list.  E.g.
      #       ["EntityName", "AttributeName", "=", "value"]
      return @jqual.addAttribQual( *qual ) unless qual[0].kind_of? Array

      # Call add_qual for each element of the array.
      qual.each { |e| add_qual( e ) }
    end

    def add_qual( qual )
      return if qual.nil?  # Nothing to add.
      return add_qual_hash( qual ) if qual.kind_of? Hash
      return add_qual_array( qual ) if qual.kind_of? Array
      @jqual.addAttribQual( qual )
    end

    def add_options( options )
      return if options.nil?
      @jqual.multipleRoots if options[:multiple_roots] || options[:root_only_multiple]
      @jqual.singleRoots   if options[:single_root]
      @jqual.root_only     if options[:root_only]      || options[:root_only_multiple]
    end

    def respond_to?( id )
      return true if @entities[id.to_s]
      return true if @jqual.respond_to? id
      super
    end

    def method_missing( id, *args, &block )
      return @entities[id.to_s] if @entities[id.to_s]
      return @jqual.send( id, *args, &block ) if @jqual.respond_to?( id )
      super id, args, &block
    end

  end # class Qualification

  class QualEntity
    def initialize( jqual, jentityDef )
      @jqual = jqual
      @jentityDef = jentityDef
    end
  end # class QualEntity

end # module Zeidon

