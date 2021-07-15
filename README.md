# Employee-Event-Challenge

## Requirments

1. Java 11
2. maven
3. docker



## Build Emvironment 


1. install and start mysql on docker 
    1. First time, ecexut `docker container run -p 3307:3306 --name mysqldb -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7`
    2. Not first time, execute `docker start mysqldb`

2. install and start rabbit-mq
    1. First time, execute `docker run -d -p 5672:5672 -p 15672:15672 --name my-rabbit rabbitmq:3-management`
    2. Not first time, execute `docker start my-rabbit`
    
    
    
### Hints

* For any reason require changing ports mentioned in above commands, then they must be changed also in application.properties files in both employee-service and event-service.

* Please follow README file inside each service