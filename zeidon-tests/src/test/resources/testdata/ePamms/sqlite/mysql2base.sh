#!/bin/sh

# Creates the sqlite DB 'base.db' from the ePamms MySql DB.

rm -f base.db
mysqldump --compatible=ansi --skip-extended-insert --compact -u ePammsTester --password=tester epammsm  | awk -f ../../../../JOE/scripts/mysql2sqllite.awk | sqlite3 base.db