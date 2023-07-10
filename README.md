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

### Run terminal terminal

java -jar SmartDubaiTest-1.0.jar

### Run through Docker

First go to Project path through Terminal and run this command

```
docker build . --tag app

docker run -it -p8080:8080 app:latest .
```

### Promo Code

- VISA 10%
- SMART 20%




#### * SwaggerUI

`http://localhost:8080/swagger-ui/index.html`

<img alt="Screenshot of Get All Request" height="500" src="./doc/Swagger.png" width="800"/>