#!/bin/bash
c="0"
java="0"
js="0"
python="0"
befunge="0"

name=""

filename="Makefile"
res=$(ls "$PWD"/**)

if echo "$res" | grep -wq "$filename"; then
    c="1"
    name="c"
fi

filename="pom.xml"
res=$(ls "$PWD"/**)

if echo "$res" | grep -wq "$filename"; then
    java="1"
    name="java"
fi

filename="package.json"
res=$(ls "$PWD"/**)

if echo "$res" | grep -wq "$filename"; then
    js="1"
    name="javascript"
fi

filename="requirements.txt"
res=$(ls "$PWD"/**)

if echo "$res" | grep -wq "$filename"; then
    python="1"
    name="python"
fi

filename="main.bf"
res=$(ls "$PWD"/**)

if echo "$res" | grep -wq "$filename"; then
    befunge="1"
    name="befunge"
fi

if [[ $c == "0" && $java == "0" && $js == "0" && $python == "0" && $befunge == "0" ]]; then
    echo "Compilation not found"
    exit 1
fi

dockerfile="0"

filename="Dockerfile"
res=$(ls "$PWD")

if echo "$res" | grep -wq "$filename"; then
    dockerfile="1"
fi

if [[ $dockerfile == "1" ]]; then
    docker build -t whanos-$name .
else
    docker build -t whanos-$name-standalone . -f /usr/local/images/$name/Dockerfile.standalone
fi