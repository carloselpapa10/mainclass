## Eventuate TRAM - SAGA sample

#### Building and Running
Main folder
```sh
$ mvn clean install
```
invoice-service folder
```sh
$ mvn docker:build
```
order-service folder
```sh
$ mvn docker:build
```

Main folder
```sh
$ docker-compose up --build
```
