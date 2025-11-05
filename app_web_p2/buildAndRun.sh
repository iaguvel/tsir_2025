#!/bin/sh
mvn clean package && docker build -t com.mycompany/app_web_p2 .
docker rm -f app_web_p2 || true && docker run -d -p 9080:9080 -p 9443:9443 --name app_web_p2 com.mycompany/app_web_p2