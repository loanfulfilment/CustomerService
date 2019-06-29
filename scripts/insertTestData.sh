#!/usr/bin/env sh

testData=$(cat ./testData.json)
mongo localhost:27017/customers --eval "db.customers.insert($testData);"