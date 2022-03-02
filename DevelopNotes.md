## Set up frameworks
This project uses Maven, frameworks used in this demo:
- Spring Boot
- Spring Data JPA
- Spring validation
- Swagger
- Lombok
- Postgres driver

Download a Spring initializer package. Add additional dependencies to the `pom.xml` file.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>

<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>3.0.0</version>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.20</version>
    <scope>provided</scope>
</dependency>

<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.2.5</version>
</dependency>
```

## Configure database
The database used is postgresql running in a docker container. Add following configurations to `application.properties` file.
```
spring.datasource.url=jdbc:postgresql://localhost:5430/demo_db
spring.datasource.username=postgres
spring.datasource.password=123123
```

The entity we use in this demo is product, to create the table in database:
```sql
CREATE TABLE product (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name TEXT,
    price DOUBLE PRECISION,
    quantity INTEGER
);
```

## API
### Create a product
Request body:
```json
{
    "name": "apple",
    "price": 2.5,
    "quantity": 20
}
```

### Update a product
Request body:
```json
{
    "id": 1,
    "name": "apple",
    "price": 2.5,
    "quantity": 20
}
```
