require 'rubygems'
#require 'bundler/setup'
require 'sinatra'

puts "Using default jar-bootstrap.jar"

load 'sinatra_framework.rb'

include_class 'com.quinsoft.zeidon.utils.JoeUtils'
include_class 'com.quinsoft.zeidon.ActivateFlags'
include_class 'com.quinsoft.zeidon.ActivateOptions'

post '/lod-server/activate' do
  puts "Inside activate post"
  puts "params = #{ params.inspect}"

  app_name = params['application']
  view_od_name = params['viewOdName']
  task = Zeidon.get_object_engine.get_app( app_name ).create_task
  puts "task_id = #{task.getTaskId}"

  options = ActivateOptions.new
  options.setActivateFlags( params['activateFlags'])
  options.setViewOd( task.jtask, view_od_name )
  qualstr = params['qualification']
  jqual = JoeUtils.deserializeView( task.jtask.getSystemTask, qualstr )
  options.setQualificationObject( jqual )

  jview = task.activateObjectInstance( options )
  jview.logObjectInstance

  system_task = task.system_task
  rest_oi = system_task.activate_empty "kzrestrc"
  rest_oi.Results.create
  rest_oi.Results.ReturnCode = 0
  rest_oi.logObjectInstance

  return_stream = JoeUtils.serializeView( rest_oi.jview )
  return_stream << JoeUtils.serializeView( jview )
  return return_stream
end

post '/lod-server/commit' do
  puts "Inside commit post"
  puts "params = #{ params.inspect}"

  app_name = params['application']
  view_od_name = params['viewOdName']
  task = Zeidon.get_object_engine.get_app( app_name ).create_task
  puts "task_id = #{task.getTaskId}"

  options = ActivateOptions.new
  options.setActivateFlags( params['activateFlags'])
  options.setViewOd( task.jtask, view_od_name )
  qualstr = params['qualification']
  jqual = JoeUtils.deserializeView( task.jtask.getSystemTask, qualstr )
  options.setQualificationObject( jqual )

  jview = task.activateObjectInstance( options )
  jview.logObjectInstance

  system_task = task.system_task
  rest_oi = system_task.activate_empty "kzrestrc"
  rest_oi.Results.create
  rest_oi.Results.ReturnCode = 0
  rest_oi.logObjectInstance

  return_stream = JoeUtils.serializeView( rest_oi.jview )
  return_stream << JoeUtils.serializeView( jview )
  return return_stream
end
