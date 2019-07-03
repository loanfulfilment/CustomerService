# CustomerService
[![Build Status](https://travis-ci.org/loanfulfilment/CustomerService.svg?branch=master)](https://travis-ci.org/loanfulfilment/CustomerService.svg?branch=master)

![alt text](https://github.com/loanfulfilment/CustomerService/blob/master/docs/LoanFulfilment.jpg)

* Insert test data into mongo database:
`cd scripts`
`./insertTestData.sh`

* Start kafka:
`docker run -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=localhost --env ADVERTISED_PORT=9092 spotify/kafka`

* Publish dummy needLoanEvent:
`./kafka-console-producer.sh --broker-list localhost:9092 --topic needLoanEvent`

* Consume customerDataAvailableForLoanProcessing:
`./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic customerDataAvailableForLoanProcessing`
