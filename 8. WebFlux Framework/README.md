# MainClass{}
### WebFlux Framework

Prerequistes

- Java 8
- Docker
- Maven

### Description

The purpose of this project is to understand the WebFlux framework and the reactive programing. To begin with, I have developed a simple application called customer-service that implements basic operations using HandlerFunctions and RouterFunctions.

Reactive Programming

>In plain terms reactive programming is about non-blocking applications that are asynchronous and event-driven and require a small number of threads to scale vertically (i.e. within the JVM) rather than horizontally (i.e. through clustering). See more [here](https://docs.spring.io/spring/docs/5.0.0.BUILD-SNAPSHOT/spring-framework-reference/html/web-reactive.html)!

### Building and Running

```sh
$ mvn clean install package -f customer-service/pom.xml
```

```sh
$ docker-compose build 
```

```sh
$ docker-compose up -d
```

### Testing

Create a new customer
```sh
$ curl -d '{"id":"2222", "name":"carlos"}' -H "Content-Type: application/json" -X POST http://localhost:5000/customer
```

Get all customers
```sh
$ curl -v localhost:5000/customers
```

Get a specific customer
```sh
$ curl -v localhost:5000/customer/2222
```

MSc Carlos Avendaño

https://www.linkedin.com/in/carlos-alberto-avenda%C3%B1o-arango-534b0a137/

