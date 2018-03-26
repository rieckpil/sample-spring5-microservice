# sample-spring5-microservice

[![CircleCI](https://circleci.com/gh/rieckpil/sample-spring5-microservice/tree/master.svg?style=svg)](https://circleci.com/gh/rieckpil/sample-spring5-microservice/tree/master)

Sample Spring 5 Microservice for evaluating new functionalities in following projects:

* Spring Security
* Spring Web
* Spring Data JPA
* Spring AOP
* Spring Batch
* Thymeleaf
* Gradle
* Protobuf

## Protobuf

Running `protoc` for compiling `.proto` files to Java classes:

```
protoc -I=. --java_out=../java nameOfProto.proto
```

## Git tags

### Create a tag

```
git tag -a v1.0.0 -m "tag message"
git push origin --tags
```

### Remove a tag

```
git tag -d tagName
git push origin :refs/tags/tagName
```
