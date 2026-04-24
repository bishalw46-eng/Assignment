# CustomerRestApi

This is a Spring Boot customer API backed by MySQL.

## Local Setup

The file `src/main/resources/application.properties` is intentionally ignored by Git because it contains local database credentials.

After cloning the project, create your own `src/main/resources/application.properties` file with values for your local MySQL setup.

Example:

```properties
spring.application.name=CustomerRestApi
server.port=7777

spring.datasource.url=jdbc:mysql://localhost:3306/customerapiapex
spring.datasource.username=root
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## API Endpoints

- `POST /customerapi/customer`
- `GET /customerapi/allcustomers`
- `GET /customerapi/customer/{id}`
- `PUT /customerapi/customer/{id}`
- `DELETE /customerapi/customer/{id}`
