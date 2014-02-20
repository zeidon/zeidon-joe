#!/bin/bash
awk '/<SQL>/,/<\/SQL/' $1 | grep -v "SQL>"