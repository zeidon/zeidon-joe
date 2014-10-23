# If this file exists in the lod-server directory it will override the jar-bootstrap.rb
# that is included in sinatra-server JAR.  Add logic and configuration to this file
# to override defaults or set up additional web entry points.

require 'rubygems'
require 'sinatra'

puts "Using local jar-bootstrap.rb"

# Loads the Zeidon sinatra framework.
load 'sinatra_framework.rb'

