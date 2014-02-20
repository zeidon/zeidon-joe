#!/bin/bash

input_file=$1
if [[ -z $input_file ]]; then
    input_file="lStudOpt.qual.json"
fi

curl --data-binary @$input_file --header "Content-Type: application/json" "http://localhost:8080/test-restserver-1.0.6-SNAPSHOT/restserver/activate?application=ZENCAs&viewOdName=lStudDpt"
