require 'rubygems'
require 'bundler/setup'
require 'sinatra'
#require 'sinatra/reloader'

load 'sinatra_framework.rb'
include_class 'com.quinsoft.zeidon.utils.JoeUtils'
post '/lod-server/activate' do
  puts "Inside activate post"
  puts "params = #{params.inspect}"

  app_name = params['application']
  view_od_name = params['viewOd']
  task = Zeidon.get_object_engine.get_app( app_name ).create_task
  puts "task_id = #{task.getTaskId}"
  qualstr = params['qualification']
  jqual = JoeUtils.deserializeView( task.jtask.getSystemTask, qualstr )
  jqual.logObjectInstance

  jview = task.activateObjectInstance( view_od_name, jqual, nil )
  jview.logObjectInstance
  return_oi = JoeUtils.serializeView( jview )
  return return_oi
end
