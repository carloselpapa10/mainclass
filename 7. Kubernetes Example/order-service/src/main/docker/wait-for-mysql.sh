#!/bin/bash

while ! exec 6<>/dev/tcp/${DATABASE_HOST}/3306; do
    echo "Trying to connect to MySQL at ${DATABASE_PORT}..."
    sleep 10
done

java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=container -jar /app.jar
