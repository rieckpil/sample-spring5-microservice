# sample-spring5-microservice

[![CircleCI](https://circleci.com/gh/rieckpil/sample-spring5-microservice/tree/master.svg?style=svg)](https://circleci.com/gh/rieckpil/sample-spring5-microservice/tree/master)
[![Heroku](https://heroku-badge.herokuapp.com/?app=sample-spring5-microservice&style=flat&svg=1)](https://sample-spring5-microservice.herokuapp.com/)

Sample Spring 5 Microservice for evaluating new functionalities in following projects:

* Spring Security
* Spring Web
* Spring Data JPA
* Spring AOP
* Thymeleaf
* Gradle
* Protobuf

## Protobuf

Running `protoc` for compiling `.proto` files to Java classes:

```
protoc -I=. --java_out=../java nameOfProto.proto
```
