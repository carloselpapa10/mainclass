# mainclass
### Spring Boot + Docker + Mysql

 - Java 8
 - Mysql 5.7.21
 - Maven
 - Swagger UI

>Note: Most part of this development is based on this good [post](https://g00glen00b.be/docker-spring-boot/).

### Requirements

 - Apache Maven (my version is 3.3.9)
 - Docker (my version is 17.12.0-ce)

### Building and Running

First, execute this docker command on a console.

```sh
$ docker run -d \
    --name demo-mysql \
    -p 3306:3306 \
    -e MYSQL_ROOT_PASSWORD=root \
    -e MYSQL_DATABASE=orderdb \
    -e MYSQL_USER=userdb \
    -e MYSQL_PASSWORD=passdb \
    mysql:latest
```
Using this command you can see if the service executed above is up (i.e. STATUS)

```sh
$ docker ps
```
Next, build order-service app

```sh
$ cd order-service/
$ mvn clean package docker:build
```
Now, you must stop demo-mysql service since both projects are using same port (i.e. 3306)

```sh
$ docker stop demo-mysql
```

Finally, run the docker-compose command on the main directory.

```sh
$ docker-compose build
$ docker-compose up
```

### Swagger UI

Order Service contains REST services to add and list orders.

```sh
http://localhost:5000/swagger-ui.html
```