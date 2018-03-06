# mainclass
### Microservices Architecture

#### Spring Boot + Docker

 - Maven
 - Swagger UI

>Note: This development is based on the first sample.
>The image architecture.jpg shows the relations between the services.

### Building and Running

First, build the application

I assume you have installed Maven in your OS. (my Maven version is Apache Maven 3.3.9)

```sh
$ mvn clean install
```
Next, launch the services using Docker Compose:

```sh
$ docker-compose build
$ docker-compose up -d
```
Once all the services are running

Eureka Server (Service Registry)
```sh
http://localhost:8761
```
Admin (Monitoring Metrics for each Microservice)
```sh
http://localhost:5000
```

Test-Producer-Service Swagger UI
```sh
http://localhost:5001/swagger-ui.html#/
```

> Note: If you follow the attached image sequence you will be able to restart the test-comsumer-service. 