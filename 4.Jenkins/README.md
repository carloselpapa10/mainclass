# mainclass
### JUnit Tests + Docker + Jenkins

 - Java 8
 - Swagger UI
 - JUnit Tests
 - Jenkins

>Note: This sample takes previous sample (i.e. 3. Docker Sample 2) as a base. JUnit Tests were applied to each method in the controller. However, a Jenkins Blueocean Docker image is used to create a pipeline that executes a continuous integration of the project. Once the pipeline has been runned we can check the Swagger UI of the Order Service.  

### Requirements

 - Docker (my version is 17.12.0-ce)
 - Git

### Run Jenkins Blueocean

First, execute the Jenkins Blueocean image from Docker.

```sh
$ docker run -u root \
	-v /var/run/docker.sock:/var/run/docker.sock \
	-p 8080:8080 \
	-p 2375:2375 \
	--name jenkins_blueocean jenkinsci/blueocean:latest
```
The image will be downloaded and installed. Then you can browse [http://localhost:8080/](http://localhost:8080/) and install Jenkins Blueocean. To get installation details follow this [post](https://jenkins.io/doc/tutorials/build-a-java-app-with-maven/) from Jenkins User Documentation. 

Next, we need to create a Jenkinsfile to perform a Continuous Integration. I used a declarative pipeline following this [post](https://liatrio.com/building-docker-jenkins-pipelines/). You can check my Jenkinsfile on my git repo [here](https://github.com/carloselpapa10/mainclass/blob/master/4.Jenkins/Jenkinsfile).

Once the Jenkisfile is created in the project we can proceed to run it. You can check the screenshots of these procedures into the pictures folder. 

We can check the services status on docker with this command.

```sh
$ docker ps
```

### Swagger UI

Order Service contains REST services to add and list orders.

```sh
http://localhost:5000/swagger-ui.html
```