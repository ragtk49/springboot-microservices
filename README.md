# Inventory Management System

IMS is a spring boot-based microservices application that is used to manage inventory and execute orders through API calls.

## Technologies used
1. Spring-Boot
2. Rest
3. MySQL
4. MongoDB
5. Netflix Eureka
6. Resilience4j
7. Kafka
8. KeyCloak
9. Zipkin
10. Prometheus
11. Grafana
12. Docker


## How to run the application using Docker

1.Run ```mvn clean package -DskipTests``` to build the applications and create the docker image locally.\
2.Run ```docker-compose up -d``` to start the applications.



## How to run the application without Docker
1.Run ```mvn clean package -DskipTests``` by going inside each folder to build the applications.\
2.Run ```docker-compose up -d``` by going inside each folder to start the applications.

## License

[MIT](https://choosealicense.com/licenses/mit/)
