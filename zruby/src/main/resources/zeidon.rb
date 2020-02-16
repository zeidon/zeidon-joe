include Java
require 'json'

java_import 'com.quinsoft.zeidon.standardoe.JavaObjectEngine'
java_import 'com.quinsoft.zeidon.CursorPosition'
java_import 'com.quinsoft.zeidon.CursorResult'

#alias :where :lambda

module Zeidon
  @@oe = nil

  def self.get_object_engine( properties_file = nil )
    return @@oe if @@oe

    if properties_file
      joe = JavaObjectEngine.getInstance( properties_file )
    else
      joe = JavaObjectEngine.getInstance( )
    end
    @@oe = ObjectEngine.new( joe )
  end

  def self.reload_object_engine( properties_file = nil )
    @@oe = nil
    JavaObjectEngine.resetInstance()
    return get_object_engine( properties_file )
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
    java_import "com.quinsoft.zeidon.ActivateFlags"
    attr_reader :jtask

    def initialize( app, jtask )
      @app = app
      @jtask = jtask
    end

    def activate view_od, qual_hash = nil
      qual = Qualification.new( @jtask, view_od )
      qual.add_qual( qual_hash ) if qual_hash
      yield qual if block_given?
      return qual.activate
    end

    def activate_empty view_od
      return @jtask.activateEmptyObjectInstance( view_od )
    end

    def get_view( view_name )
      return @jtask.getViewByName( view_name )
    end

    def respond_to?( id )
      return @jtask.respond_to?( id ) || super
    end

    def system_task
      return Task.new( @app.oe.ZeidonSystem, @jtask.getSystemTask )
    end

    def method_missing( id, *args, &block )
      return @jtask.send( id, *args, &block ) if @jtask.respond_to?( id )
      return ViewDef.new( self, id ) if is_lod_name?( id )
      super
    end

    private

    def is_lod_name?( str )
      begin
        @jtask.getApplication.getLodDef( @jtask, str )
        return true
      rescue Exception => e
        return false
      end
    end
  end # Task

  class ViewDef
    attr_reader :task, :lod_name

    def initialize( task, lod_name )
      @task = task
      @lod_name = lod_name.to_s
    end

    def activate qual_hash
      task.activate( lod_name, qual_hash )
    end

    def activate_empty
      task.activate_empty( lod_name )
    end

  end

  class Java::ComQuinsoftZeidonStandardoe::ViewImpl
    def respond_to?( id )
      return ! getLodDef().getEntityDef( id, false).nil? || super
    end

    def method_missing( id, *args, &block )
      return self.cursor( id.to_s ) unless getLodDef().getEntityDef( id.to_s, false).nil?

      caller = get_object_operation_caller( id, *args )
      return caller if caller

      super
    end

    def copy_view
      return self.newView
    end

    def to_s
      self.toString
    end

    def to_json
      self.serializeOi.compressed.asJson.toString
    end

    def to_hash
      JSON.parse( self.to_json )
    end

    private

    def get_object_operation_caller( id, *args )
      nil
    end

  end # View

  class Qualification
    java_import "com.quinsoft.zeidon.utils.QualificationBuilder"

    def initialize(jtask, view_od_name)
      @jqual = QualificationBuilder.new( jtask )
      @jqual.setLodDef( view_od_name )
      @entities = {}
      jloddef = @jqual.getLodDef
      jloddef.getEntityDefs.each do |ve|
        name = ve.getName.to_s
        @entities[ name ] = QualEntity.new( @jqual, ve )
      end
    end

    def add_qual( qual )
      return if qual.nil?  # Nothing to add.
      return if qual.size == 0
      @jqual.loadFromJsonString( qual.to_json )
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


  # Re-open EntityCursor definition and add some Ruby stuff
  class Java::ComQuinsoftZeidonStandardoe::EntityCursorImpl

    def name
      return entity_def.name
    end

    def has_attribute( attr_name )
      return ! getEntityDef.getAttribute( attr_name.to_s, false ).nil?
    end

    def attributes( options = {} )
      include_null_attributes = options.fetch( :include_null, true )
      include_hidden_attributes = options.fetch( :include_hidden, false )
      list = getAttributes( include_null_attributes )
      list = list.reject{ |a| a.attribute_def.hidden? } if ! include_hidden_attributes
      return list
    end

    def to_hash
      Hash[ attributes.map do |attr|
        [attr.name, attr.getValue]
      end ]
    end

    def attributes=( attribute_hash )
      attribute_hash.each do |name, value|
        if has_attribute( name )
          attrib = getAttribute( name.to_s )
          attrib.setValue( value )
        end
      end
      return self
    end

    def each_attrib( options = {} )
      list = attributes( options )
      list.each do |attrib|
        yield attrib
      end
    end

    # Loop through each sibling for this cursor.  Sets the cursor.
    def each( args = {} )
      scoping = args[:scope] || args[:scoping] || args[:under] || ""
      if scoping == :oi
        iter = allEntities()
      else
        iter = eachEntity( scoping )
      end

      while iter.hasNext
        yield iter.next
      end
    end

    def get_key
      # We assume one and-only-one key.
      getAttribute( entity_def.keys[ 0 ] )
    end

    def create( *args )
      position = CursorPosition::NEXT
      attribute_hash = nil

      args.each do |arg|
        if arg.kind_of?( CursorPosition )
          position = arg
        elsif arg.kind_of?( Hash )
          attribute_hash = arg
        else
          raise "Unknown argument type for create(): #{arg.class}"
        end
      end

      rc = createEntity( position )
      self.attributes = attribute_hash if attribute_hash
      return rc
    end

    # Loop through the child *cursors* of this cursor.
    def each_child
      getEntityDef.getChildren.each do |jve|
        yield getView.cursor( jve.getName )
      end
    end

    def respond_to?( id )
      return true if has_attribute( id )

      # Check to see if it's an assignment (e.g. view.Entity.Attr = value )
      if id.to_s =~ /(.*)=/
        name = $1
        return true if has_attribute( name )
      end

      super
    end

    def set( scoping: nil, &block )
      iter = scoping ? eachEntity( scoping ) : eachEntity()
      while iter.hasNext
        ei = iter.next
        if yield ei, getView
          return ei
        end
      end

      return false
    end

    #java_alias :set_first_by_attr, :set_first, [java.lang.String, java.lang.Object]
    def set_firstx( *args, &block )
      if args.length == 1 and args[0].kind_of?( Hash )
        arg_hash = args[0]
        if arg_hash.length == 1
          attr = arg_hash.keys.first.to_s
          val = arg_hash.values.first
          return java_send :setFirst, [java.lang.String, java.lang.Object], attr, val
        end
      end

      super
    end

    # Simple wrapper for includeSubobject method.
    def include( *args )
      includeSubobject( *args )
    end

    # Simple wrapper for excludeEntity method.
    def exclude( *args )
      includeEntity( *args )
    end

    def exists?
      return has_any
    end

    def method_missing( id, *args, &block )
      return getAttribute( id.to_s ) if has_attribute( id )

      # Check to see if it's an assignment (e.g. view.Entity.Attr = value )
      if id.to_s =~ /(.*)=/
        name = $1
        if has_attribute( name )
          attrib = getAttribute( name )
          attrib.setValue( args[0] )
          return attrib
        end
      end

      super
    end
  end # EntityCursorImpl

  class Java::ComQuinsoftZeidonStandardoe::EntityInstanceImpl

    def name
      return entity_def.name
    end

    def has_attribute( attr_name )
      return ! getEntityDef.getAttribute( attr_name.to_s, false ).nil?
    end

    def attributes( options = {} )
      include_null_attributes = options.fetch( :include_null, true )
      include_hidden_attributes = options.fetch( :include_hidden, false )
      list = getAttributes( include_null_attributes )
      list = list.reject{ |a| a.attribute_def.hidden? } if ! include_hidden_attributes
      return list
    end

    def each_attrib( options = {} )
      list = attributes( options )
      list.each do |attrib|
        yield attrib
      end
    end

    def get_key
      # We assume one and-only-one key.
      attribute( entity_def.keys[ 0 ] )
    end

    def respond_to?( id )
      return true if has_attribute( id )

      # Check to see if it's an assignment (e.g. view.Entity.Attr = value )
      if id.to_s =~ /(.*)=/
        name = $1
        return true if has_attribute( name )
      end

      super
    end

    def method_missing( id, *args, &block )
      return getAttribute( id.to_s ) if has_attribute( id )

      # Check to see if it's an assignment (e.g. view.Entity.Attr = value )
      if id.to_s =~ /(.*)=/
        name = $1
        if has_attribute( name )
          attrib = getAttribute( name )
          attrib.setValue( args[0] )
          return attrib
        end
      end

      super
    end
  end # EntityInstanceImpl

  # Re-open AttributeInstanceImpl definition and add some Ruby stuff
  class Java::ComQuinsoftZeidonStandardoe::AttributeInstanceImpl

    def entity_name
       return getAttributeDef.getEntityDef.getName
     end

     def to_f
      getDouble().to_f
    end

    def internal_value
      getValue()
    end

    def operators( op, value )
      #puts "Op = #{op.to_s} value = #{value.to_s}"
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
      return self.equals( value )
    end

    def !=( value )
      return ! self.equals( value )
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

    # This will attempt to convert an attribute value to a Ruby type.  This is
    # necessary so that Ruby can parse something like:
    #    n = 4 + view.EntityName.IntAttribute
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

    # Override the toString() method.
    java_signature 'String toString()'
    def to_s
      return getString("") || ""
    end

    def respond_to?( id )
      return getAttributeDef.respond_to?( id ) || super
    end

    def method_missing( id, *args, &block )
      return getAttributeDef.send( id, *args, &block ) if getAttributeDef.respond_to?( id )
      super
    end

  end # AttributeInstanceImpl

end # module Zeidon
