# sample-spring5-microservice

[![CircleCI](https://circleci.com/gh/rieckpil/sample-spring5-microservice/tree/master.svg?style=svg)](https://circleci.com/gh/rieckpil/sample-spring5-microservice/tree/master)

Sample Spring 5 Microservice for evaluating new functionalities in following projects:
* Spring Security
* Spring Web
* Spring Data JPA
* Spring AOP
* Thymeleaf

## Protobuf

Running `protoc` for compiling `.proto` files to Java classes:

```
protoc -I=. --java_out=../java nameOfProto.proto
```
