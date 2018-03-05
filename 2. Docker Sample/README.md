# mainclass
Microservices Architecture

Spring Boot + Docker

Maven
Swagger UI

Note: this development is based on the first sample (i.e. 1. Microservices Architecture - Sample).
The image architecture.jpg shows the relations between the services.

Building and Running

First, build the application

I assume you have installed Maven in your OS. (my Maven version is Apache Maven 3.3.9)

mvn clean install

Next, launch the services using Docker Compose:

docker-compose build
docker-compose up -d
 
Once all the services are running

Swagger UI
http://localhost:5001/swagger-ui.html#/

If you follow the attached image sequence you will be able to restart the test-comsumer-service. 