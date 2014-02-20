#!/bin/sh
# user=dgc, pw=d
sudo /etc/init.d/mysql stop
sudo mysqld --basedir=/usr --datadir=/var/lib/mysql --user=mysql --pid-file=/var/run/mysqld/mysqld.pid --skip-external-locking --port=3306 --socket=/var/run/mysqld/mysqld.sock --lower_case_table_names=1
#mysql --user=root --password -h localhost noa


