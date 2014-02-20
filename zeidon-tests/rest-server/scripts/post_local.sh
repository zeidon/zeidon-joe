#!/bin/bash

curl -d "abc" --header "Content-Type: text/plain" "http://localhost:8080/restserver/restserver/activate?application=ZENCAs&viewOdName=mStudent"

echo ""