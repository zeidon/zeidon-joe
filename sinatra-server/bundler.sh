#!/bin/bash
GEM_PATH=`pwd`/lib/jruby/1.8 java \
-jar ~/.m2/repository/org/jruby/jruby-complete/1.6.8/jruby-complete-1.6.8.jar \
-S lib/jruby/1.8/bin/bundle install --path lib/