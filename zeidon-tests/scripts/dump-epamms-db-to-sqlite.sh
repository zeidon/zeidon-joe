#!/bin/bash

# Dumps a epamms DB from MySql to Sqlite file.

DB_HOST=localhost
DB_SCHEMA=epamms
DB_USER=dgc
DB_PASSWORD=password

echo "Dumping db..."
#mysqldump --compatible=ansi --skip-extended-insert --compact -u $DB_USER -p$DB_PASSWORD --lock-tables=false -h $DB_HOST $DB_SCHEMA > /tmp/epamms-dump.sql

# It's possible that dos2unix might need to be run here to convert CRLF in the .sql dump.
# May not be necessary on cygwin
dos2unix /tmp/epamms-dump.sql

echo "Massaging SQL to fit sqlite standards..."
awk -f mysql2sqllite.awk /tmp/epamms-dump.sql > /tmp/epamms-dump-sqlite.sql

echo "Importing into sqlite db..."
rm -f epamms.sqlite > /dev/null
sqlite3 epamms.sqlite < /tmp/epamms-dump-sqlite.sql &> /tmp/dump.log

tail /tmp/dump.log

echo "epamms.sqlite created"
