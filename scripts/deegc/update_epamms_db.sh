#!/bin/bash

sudo cp -f /home/dchristensen/zeidon/java_project/JoeTests/testdata/ePamms/mysql/ePammsM/* /var/lib/mysql/epammsm
sudo chown -R mysql:mysql /var/lib/mysql/epammsm
sudo service mysql restart
