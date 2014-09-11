require 'rubygems'
require 'java'
require 'jmx4r'
require 'sinatra'
require 'sinatra/reloader'
require 'haml'
require 'pp'
require 'fastercsv'

load '../zeidon.rb'
enable :sessions
set :port => 4568

def get_jmx_manager
  session[:jmx_port] = session[:jmx_port] || 9001
  puts "Using #{session[:jmx_port]} for jmxport"
  JMX::MBean.establish_connection :host => "localhost", :port => session[:jmx_port]
  manager = JMX::MBean.find_by_name "com.quinsoft.zeidon.jmx:type=ObjectEngineMonitor"
end

get '/zeidon/manager' do
  manager = get_jmx_manager
  @view_list = manager.view_list.sort {|a,b| a['taskId'] <=> b['taskId'] }
  @runtime = manager.runtime_properties.sort_by{|k,v| k}
  
  haml <<-code
%title Zeidon OE JMX Manager
%h2 Zeidon OE JMX Manager
%h3 Named views
%table{:border=>1}
  %tr
    %th Task ID
    %th View Name
    %th LodDef
    %th OI ID
  - @view_list.each do |view|
    %tr
      %td= view['taskId']
      %td= view['name']
      %td= view['lodDef']
      %td= view['oiId']
      %td
        %a{:href => url("/zeidon/manager/dropview?taskId=" + view['taskId'] + "&name=" + view['name'])}
          = "Drop"
<br/>
%h3 Runtime Properties
%table{:border=>1}
  %tr
    %th Property
    %th Value
  - @runtime.each do |value|
    %tr
      %td= value[0]
      %td= value[1]
code
end

get '/zeidon/manager/dropview' do
  manager = get_jmx_manager
  pp params
  manager.drop_view_by_name params[:taskId], params[:name]
  return "View '#{params[:name]}' dropped"
end
