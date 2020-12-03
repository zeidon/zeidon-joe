#!/bin/bash

input_file=$1
if [[ -z $input_file ]]; then
    input_file=activate-data.txt
fi

curl --data-binary @$input_file --header "Content-Type: application/json" "http://localhost:8080/restserver/restserver/activate?application=ZENCAs&viewOdName=lStudDpt"
