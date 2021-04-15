#!/bin/bash

set -x
for i in {0..1000}
do
	echo $i
	curl --location --request POST 'http://localhost:8080/oraclejms/send?msg=hello' \
--data-raw ''
done
