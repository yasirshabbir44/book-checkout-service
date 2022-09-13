# Book Service

THis REST API for an online bookstore, where the user can perform the following operations:

- CRUD operations on Books 
- Checkout operation for single or multiple books which will return the total payable amount.

### Project Description

In this Project we used following 
- Spring Boot
- Java 14
- SpringData along with InMemoryDB
- SwaggerUI
- Lombok
- Docker


### Swagger

SwaggerUI can accessible on following URL

http://localhost:8080/swagger-ui/index.html#/

![SwaggerUI screenshot](https://i.postimg.cc/HxkBT840/Screen-Shot-2022-09-13-at-9-27-53-PM.png)





**How to run project through Docker**

First go to Project path through Terminal and run this command

```
docker build -t="yasir/docker-bookservice" .
```

*then run last command and boom*
 ```
docker run -p 9090:8080 {IMAGE_ID}}
```


![Docker Screen shot to run the image](https://i.postimg.cc/DfqdRgTV/Screen-Shot-2022-09-13-at-9-33-16-PM.png)