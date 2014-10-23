require 'rubygems'
require 'sinatra'
require 'haml'
require 'pp'
require 'fastercsv'
require "nokogiri"
require 'pathname'

load "zeidon.rb"
include_class 'com.quinsoft.zeidon.CursorPosition'
include_class 'java.util.jar.JarFile'

puts "Loaded sinatra_framework.rb from #{__FILE__}"
enable :sessions
set :port, ENV['SINATRA_PORT'].to_i || 4567

# ===== Generic methods =====

set :domain_mappings, {}

# This sets up a mapping between an entity name and the LOD that is used to retrieve a list.
set :lod_for_entity_list, {}

# The list of loddef's for each application.
@@app_loddef_list={}

def domain_mapping( domain_name, mapper )
  settings.domain_mappings[domain_name] = mapper
end

def lod_for_entity_list( lod_name, entity_name )
  settings.lod_for_entity_list[entity_name] = lod_name
end

# Retrieves the Zeidon task by task ID and returns a Ruby
# Task wrapper.
def get_task( args = { :create => true } )
  app_name = params[:application]
  application = Zeidon.get_object_engine.get_app app_name
  task_id = session[:zeidon_task_id]
  jtask = Zeidon.get_object_engine.getTaskById( task_id )
  if jtask.nil?
    return unless args[:create]
    raise "Must specify app name for :create" unless app_name
    task = Zeidon.get_object_engine.get_app( app_name ).create_task
    add_task_to_session task
    return application, task
  end

  app = Zeidon.get_object_engine.get_app( app_name )
  return application, task = Zeidon::Task.new( app, jtask )
end

def add_task_to_session( task )
  task_id = task.getTaskId
  session[:zeidon_task_id] = task_id
end

def input_field_for_attrib( attrib, args = {} )
  html = ''
  name="input[#{attrib.entity_name}][#{attrib.name}]"

  # Hidden attributes just get included as hidden fields.
  if attrib.is_hidden
    return <<-code
    <input type='hidden' 
           name='#{name}'
           value='#{attrib.to_s}' />
code
  end

  jdomain = attrib.getDomain
  domain_type = jdomain.getDomainType.to_s
  domain_name = jdomain.getName

  # Check to see if there is a app-specific domain handler.
  if settings.domain_mappings[domain_name]
    return settings.domain_mappings[domain_name].input_field_for_attrib( attrib, name, args )
  end

  # Is this domain a table domain?  If so, then create a combo-box.
  if domain_type == 'TABLE'
    @list = jdomain.getTableEntries( @task.jtask, nil )
    puts "list for #{jdomain.getName} = #{@list}"
    return haml <<-code
%td
  %select{:name => '#{name}'}
    - @list.each do |entry|
      %option{:selected => (entry.getInternalValue == "#{attrib.to_s}") }
        = entry.getExternalValue
code
  end

  # If size is > 200 then use a text area.
  if attrib.getLength > 200
    str = attrib.to_s || ""
    lines = str.lines.count + 5
    lines = 20 if lines > 20
    return <<-code
<td>
<textarea name='#{name}' rows='#{lines}' cols='60'>#{find_and_preserve(str)}</textarea>
</td>
code
  end

  type = 'text'
  html << <<-code
    <td>
    <input type='#{type}' 
           name='#{name}'
           value='#{attrib.to_s}'
  code
  html << "readonly='readonly'" if attrib.isGenKey || ! attrib.isUpdate
  html << "/>\n</td>"
  return html
end

def input_fields_for_entity( entity, args = {} )
#  puts "Entity methods = #{entity.methods.sort.join("\n").to_s}"
  args = args.clone # So we can add to the hash without changing original one.
  html = "<table>\n"
  entity.attributes.each do |attrib|
    html << "<tr>\n<td valign=\"top\">#{attrib.name}:</td>\n" if ! attrib.is_hidden
    html << input_field_for_attrib( attrib, args )
    html << "\n</tr>\n" if ! attrib.is_hidden
  end
  html << "</table>\n"
  return html
end

def list_entities( top_entity, args = {} )
  args = args.clone # So we can add to the hash without changing original one.

  # Find children of 'entity' that have a max cardinality of 1.  We will
  # display them all in one row.
  entity_list = [ top_entity ]
  jtop_view_entity = top_entity.getEntityDef
  if @command != 'list'  # Only show children if we're aren't listing roots.
    jtop_view_entity.getChildren.each do | jchildentity |
      entity_list << @view.cursor( jchildentity.name ) if jchildentity.getMaxCardinality <= 1
    end
  end

  # Add column headers.
  html = "<table border='1'>\n<tr><th></th>"
  html << "<th></th>"  if jtop_view_entity.getAutoSeq != nil 
  entity_list.each do |entity|
    html << "<th>#{entity.get_name}--></th>" if entity != top_entity
    entity.attributes.each do |attrib|
      next if attrib.is_hidden
      html << "<th>#{attrib.name}</th>"
    end
  end

  html << "</tr>\n"

  url_param = "&entity=#{top_entity.get_name}"
  url_param << "&viewname=#{@view_name}" if args[:viewname]

  top_entity.each do # For each top-level entity instance...
    html << "<tr>"

    links = [] # We'll create a list of edit links and then concat them at the end.
    if args[:include_only] # If true, then we're displaying a list of entities to include
      links << "<a href='/#{@application}/select/#{@loddef}?id=#{top_entity.get_key.to_str}&entity=#{@entity}&viewname=#{@view_name}'>Select</a>"
    else
      links << "<a href='/#{@application}/edit/#{@loddef}?id=#{top_entity.get_key.to_str}#{url_param}'>Edit</a>" if jtop_view_entity.isUpdate
      links << "<a href='/#{@application}/exclude/#{@loddef}?id=#{top_entity.get_key.to_str}#{url_param}'>Exclude</a>" if jtop_view_entity.isExclude
      links << "<a href='/#{@application}/delete/#{@loddef}?id=#{top_entity.get_key.to_str}#{url_param}'>Delete</a>" if jtop_view_entity.isDelete
    end

    html << "<td>" << links.join("<br/>") << "</td>"

    # Add move up/down links.
    if jtop_view_entity.getAutoSeq != nil 
      html << "<td>"
      html << "<a href='/#{@application}/moveup/#{@loddef}?id=#{top_entity.get_key.to_str}#{url_param}'>Move Up</a><br/>"
      html << "<a href='/#{@application}/movedown/#{@loddef}?id=#{top_entity.get_key.to_str}#{url_param}'>Move Down</a>"
      html << "</td>"
    end

    entity_list.each do |entity|
      html << "<td></td>" if entity != top_entity
      entity.attributes.each do |attrib|
        next if attrib.is_hidden
        if entity.isNull or attrib.is_null?
          html << "<td></td>\n"
        else
          value = attrib.getStringFromAttribute(attrib.name, nil)
          if value && value.length > 100
            value = /^([^\n]*)/.match(value)[1][0..100] + "..."
          end
          html << "<td>#{value}</td>\n"
        end
      end
    end

    html << "</tr>\n"
  end
  
  html << "</table>\n"

  if ! args[:include_only]
    if top_entity.getEntityDef.isCreate
      html += haml <<-code
%p
%form{ :action => url('/#{@application}/new/#{@loddef}'),
       :method => 'get', :enctype => 'multipart/form-data', :name => 'New_#{top_entity.get_name}' }
  %input{:type => "hidden", :name => "viewname", :value => "#{@view_name}"}
  %input{:type => "hidden", :name => "entity", :value => "#{top_entity.get_name}"}
  %input{:type => "submit", :value => "New #{top_entity.get_name}", :class => "button" }
code
    elsif top_entity.getEntityDef.isInclude
      html += haml <<-code
%p
%form{ :action => url('/#{@application}/include/#{@loddef}'),
       :method => 'get', :enctype => 'multipart/form-data', :name => 'Include_#{top_entity.get_name}' }
  %input{:type => "hidden", :name => "viewname", :value => "#{@view_name}"}
  %input{:type => "hidden", :name => "entity", :value => "#{top_entity.get_name}"}
  %input{:type => "submit", :value => "Include #{top_entity.get_name}", :class => "button" }
code
    end
  end

  return html
end

def activate_for_edit( args = {} )
  raise "ID not specified for edit" if params[:id].nil?

  jloddef = @application.getLodDef( @task.jtask, @loddef )
  jroot = jloddef.getRoot
  jkey = jroot.getKeys[0] # We're assuming there is one and only one key.
  @view = @task.activate @loddef, :qual => [ jkey.getName, params[:id] ]
  @view_name = "Edit_#{params[:loddef]}"
  @view.set_name( @view_name )
  return @view_name
end

def get_loddef_list app_name
  puts "Getting loddef_list from #{@@app_loddef_list}"

  return @@app_loddef_list[ app_name ] if @@app_loddef_list[ app_name ]

  @@app_loddef_list[ app_name ] = []

  puts "classpath="
  $CLASSPATH.each{|cp| puts "-> #{cp}"}
  oe = Zeidon.get_object_engine
  bindir = oe.get_app( app_name ).getBinDir
  puts "bindir = #{bindir}"
  regex = /^#{bindir}.*\.(XOD|xod)$/

  loader = oe.getClassLoader( "" )
  urls = loader.getURLs
  urls.each do |url|
    begin
      jar = JarFile.new( url.getPath )
      jar.entries.each do |entry|
        @@app_loddef_list[ app_name ] << File.basename( entry.toString, "." + $1) if entry.toString =~ regex
      end
    rescue
      puts "Error trying to load jar file #{url.getPath} protocol #{url.getProtocol}"
    end
  end

  @@app_loddef_list[ app_name ].sort!
end

# ===== Helper methods =====

helpers do
  
  def top_links
    html = "<table><tr>"

    # Add link to list of all LodDefs
    html << "<td>|</td>"
    html << "<td><a href='" + url("/#{@application}/list") + "'>#{@application} List</a></td>"

    # Add link to list of root-level instances.
    if @loddef
      html << "<td>|</td>"
      html << "<td><a href='" + url("/#{@application}/list/#{@loddef}") + "'>#{@loddef} Root List</a></td>"
   end

    # If we're editing/viewing a loddef, then check to see if we need a "to root" link.
    if @loddef and @entity
      jloddef = @application.getLodDef( @task.jtask, @loddef )
      loddef_root = jloddef.getRoot.getName
      if loddef_root != @entity
        html << "<td>|</td>"
        html << "<td><a href='" + url("/#{@application}/#{@command}/#{@loddef}?viewname=#{@view_name}") + "'>Top of #{@loddef}</a></td>"
      end

      jentityDef = jloddef.getEntityDef( @entity )
      jparent = jentityDef.getParent
      if ! jparent.nil? and jparent.getName.to_s != loddef_root.to_s
        puts "jparent=#{jparent.getName}\nentity=#{@entity}"
        html << "<td>|</td>"
        html << "<td><a href='" + url("/#{@application}/#{@command}/#{@loddef}?viewname=#{@view_name}&entity=#{jparent.getName}") + "'>Up to #{jparent.getName}</a></td>"
      end
    end

    html << "<td>|</td>"
    html << "</tr></table><br/>"
  end

  def bottom_links
    puts "bottom_links"
    html = <<-code
          <br/><br/>
            <table><tr>
               <td><a href='#{url("startbrowser")}'>Start Browser</a></td>
            </tr></table>
code
    return html
  end

  def display_messages
    return "" if @messages.nil? || @messages.length == 0
    return haml <<-code
%table
  - @messages.each do |msg|
    %tr
      %td
        %font{:color => "red"}
          = msg
%br
code
  end

  def top_layout
    return top_links + display_messages
  end

end

# ===== Route methods =====

get '/startbrowser' do
  application = Zeidon.get_object_engine.startBrowser
end

# This method is called before the route methods are called and it sets up some standard
# member variables.
def setup_environment
  puts "===== setup_environment ======"
  puts Time.now
  pp params
  pp session

  # Set some standard variables.
  @command = params[:cmd]
  @loddef = params[:loddef]
  @view_name = params[:viewname]
  @entity = params[:entity]
  @application, @task = get_task
  @view = @task.get_view( @view_name ) if @view_name
  puts "view = #{@view}"
  @messages = session[:messages]
  session[:messages] = []

  if @loddef
    jloddef = @application.getLodDef( @task.jtask, @loddef )
    # Default entity to root of loddef
    if @entity.nil?
      @entity = jloddef.getRoot.getName
      @parent_entity = @entity
      @root_entity = @entity
    else
      jparent = jloddef.getEntityDef( @entity ).getParent
      @parent_entity = jparent.nil? ? @entity : jparent.getName
      @root_entity = jloddef.getRoot.getName
    end
  end
end

before '/:application/:cmd/:loddef' do
  setup_environment
end

get '/:application/list' do
  setup_environment
  @command = 'list'
  haml <<-code
%title #{@application} List
%h2 LodDef list for #{@application}
= top_layout
%table
  - get_loddef_list( @application.to_s ).each do |loddef|
    %tr
      %td
        %a{:href => url("/#{@application}/list/" + loddef )}
          = "List"
          = loddef
= bottom_links
code
end

get '/:application/list/:loddef' do
  @view = @task.activate @loddef, :options => {:root_only_multiple => true}
  @view.setName @loddef + "_list"
  return haml <<-code
%title #{@loddef} List
%h2 #{@loddef} List
= top_layout
%table
  %tbody
    = list_entities( @view.cursor( "#{@entity}" ), :root_list => true )
code
end

# Create a new empty entity and redirect to edit it.
get '/:application/new/:loddef' do
  # If the view doesn't exist create the OI.
  if @view.nil?
    @view = @task.activate_empty_object_instance @loddef
    @view_name = "New_#{@loddef}"
    @view.set_name( @view_name )
  end
  
  @view.cursor( @entity ).createEntity
  u = url("/#{@application}/edit/#{@loddef}?viewname=#{@view_name}&entity=#{@entity}")
  redirect u
end

get '/:application/edit/:loddef' do
  # If there is no named view then activate one using the URL params.
  activate_for_edit if @view.nil?
  cursor = @view.cursor( @entity )
  key = cursor.get_key.getName
  cursor.setFirst(key, params[:id] )

  # If the entity being edited is not the root then we won't have 
  # a save button but an "accept".
  @save_button_text = ( @entity == @root_entity ) ? "Save" : "Accept"

  return haml <<-code
%title #{@application} - Edit #{@loddef}.#{@entity}
%h2 Edit #{@entity}
= top_layout
%form{ :action => url('/#{@application}/save/#{@loddef}'), 
       :method => "post", :enctype => 'multipart/form-data', :name => 'New_#{@entity}'}
  %fieldset
    %input{:type => "hidden", :name => "viewname", :value => "#{@view_name}"}
    %input{:type => "hidden", :name => "entity", :value => "#{@entity}"}
    = input_fields_for_entity( @view.cursor( @entity ) )
  %input{:type => "submit", :name => "save", :value => "#{@save_button_text}", :class => "button"}
  %input{:type => "submit", :name => "duplicate", :value => "Duplicate", :class => "button"}
- @view.cursor( @entity ).each_child do |child_cursor|
  - cname = child_cursor.get_name
  %p
  %table{:border=>"1"}
    %tr
      %td= cname
    %tr
      %td= list_entities( child_cursor, :viewname => "#{@view_name}" )
code
end

get '/:application/delete/:loddef' do
  # If there is no named view then we are deleting the entire OI.
  if ! @view.nil?
    # We're deleting just an entity in the existing OI.
    puts "Deleting child entity"
    cursor = @view.cursor( @entity )
    key = cursor.get_key.getName
    cursor.setFirst(key, params[:id] )
    instance = cursor.jcursor.getEntityInstance
    puts "Deleting #{instance}"
    session[:messages] << "Entity #{instance} has been deleted and will be committed when OI is saved."
    cursor.deleteEntity
    return redirect to("/#{@application}/edit/#{@loddef}?viewname=#{@view_name}&entity=#{@parent_entity}")
  end

  return "Not supported yet"
end

get '/:application/exclude/:loddef' do
  return "No view found" if @view.nil?

  # We're deleting just an entity in the existing OI.
  puts "Excluding child entity"
  cursor = @view.cursor( @entity )
  key = cursor.get_key.getName
  cursor.setFirst(key, params[:id] )
  instance = cursor.jcursor.getEntityInstance
  puts "Excluding #{instance}"
  session[:messages] << "Entity #{instance} has been excluded and will be committed when OI is saved."
  cursor.excludeEntity
  return redirect to("/#{@application}/edit/#{@loddef}?viewname=#{@view_name}&entity=#{@parent_entity}")
end

get '/:application/moveup/:loddef' do
  puts "Moving child entity UP"
  cursor = @view.cursor( @entity )
  key = cursor.get_key.getName
  cursor.setFirst(key, params[:id] )
  temp = @view.new_view
  if temp.cursor( @entity ).setPrev == CursorResult::SET
    instance = cursor.jcursor.getEntityInstance
    puts "Moving #{temp.cursor( @entity ).getEntityInstance} before #{instance}"
    temp.cursor( @entity ).moveSubobject( CursorPosition::PREV, instance )
  else
    puts "Instance is first instance so nothing to move"
  end

  return redirect to("/#{@application}/edit/#{@loddef}?viewname=#{@view_name}&entity=#{@parent_entity}")
end

get '/:application/movedown/:loddef' do
  puts "Moving child entity DOWN"
  cursor = @view.cursor( @entity )
  key = cursor.get_key.getName
  cursor.setFirst(key, params[:id] )
  temp = @view.new_view
  if temp.cursor( @entity ).setNext == CursorResult::SET
    instance = cursor.jcursor.getEntityInstance
    puts "Moving up: #{instance}"
    cursor.moveSubobject( CursorPosition::NEXT, temp.cursor( @entity ).getEntityInstance )
  else
    puts "Instance is last instance so nothing to move"
  end

  return redirect to("/#{@application}/edit/#{@loddef}?viewname=#{@view_name}&entity=#{@parent_entity}")
end

post '/:application/save/:loddef' do
  raise ":viewname not specified" if @view_name.nil?

  # If 'duplicate' param is specified then we really want to duplicate the current
  # OI and send the user off to edit it.
  if ! params[ 'duplicate' ].nil?
    puts "Duplicating OI"
    session[:messages] << "OI Duplicated.  Edit below."
    new_view = @view.duplicateOi
    new_view.setName @view_name
    return redirect to("/#{@application}/edit/#{@loddef}?viewname=#{@view_name}")
  end

  input = params[:input]
  input.each_pair do |entity_name, values|
    cursor = @view.cursor( entity_name )
    values.each_pair do |attrib_name, value|
      attrib = cursor.get_attribute( attrib_name )
      attrib.set( value ) unless attrib.is_hidden || attrib.isGenKey || ! attrib.isUpdate
    end
  end

  next_view_entity = ""
  if @entity == @root_entity
    @view.commit
    session[:messages] << "#{@loddef} OI saved."
  else
    session[:messages] << "#{@loddef}.#{@entity} updated."
    next_view_entity = "&entity=#{@parent_entity}"
  end

  return redirect to("/#{@application}/edit/#{@loddef}?viewname=#{@view_name}#{next_view_entity}")
end

def activate_include_source
  list_view_name = "#{@entity}_List"
  # We can't cache the view list until we drop the cache if an
  # entity in the cache is updated/created/deleted.
  # @include_source = @task.get_view list_view_name
  # return if @include_source

  lod_name = settings.lod_for_entity_list[ @entity ] || @entity

  @include_source = @task.activate lod_name, :options => { :root_only_multiple => true }
  @include_source.set_name( list_view_name )
  @select_entity = @include_source.getLodDef.getRoot.name
end

get '/:application/include/:loddef' do
  activate_include_source
  list_entities @include_source.cursor( @select_entity ), :include_only => true, :viewname => @view_name
end

# User selected an entity to be included.
get '/:application/select/:loddef' do
  @select_entity = settings.lod_for_entity_list[ @entity ] || @entity
  list_view_name = "#{@entity}_List"
  @include_source = @task.get_view( list_view_name )

  cursor = @include_source.cursor( @select_entity )

  key = cursor.get_key.getName
  cursor.setFirst(key, params[:id] )

  @view.cursor( @entity ).include_subobject( cursor )

  return redirect to("/#{@application}/edit/#{@loddef}?viewname=#{@view_name}&entity=#{@parent_entity}")
end

get '/' do
  puts "Listing applications"

  haml <<-code
%title Applications List
%h2 List of Available Applications
%table
  - Zeidon.get_object_engine.applications.each do |application|
    - if application != "ZeidonSystem"
      %tr
        %td
          %a{:href => url("/" + application + "/list" )}
            = "View"
            = application
= bottom_links
code

end

#==============================================================
# Activate/Commit functions
#==============================================================
post '/:application/activate/:loddef' do
  
end
