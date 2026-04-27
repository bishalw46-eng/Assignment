# EmployeeMVC

Classic Spring MVC Employee Management web application using JSP, Spring Data JPA, Hibernate, MySQL, Maven, and Tomcat. This project does not use Spring Boot.

## Features

- List all employees
- Show total employee count
- Search employees by name
- Add a new employee
- Edit an existing employee
- Delete an employee
- View employee details
- Validate required fields and salary greater than 0
- Show an error when an email address already exists

## Requirements

- Java 11
- Maven
- MySQL
- Tomcat 9

Tomcat 9 is required because this project uses the classic `javax.servlet` APIs. Tomcat 10 uses `jakarta.servlet` and is not compatible with this WAR without dependency changes.

## Database Setup

Create the database and table:

```bash
mysql -u root -p < database/employeemvc_schema.sql
```

Verify that the database and table exist:

```bash
mysql -u root -p -e "SHOW DATABASES LIKE 'employeedb'; SHOW TABLES FROM employeedb LIKE 'employees';"
```

Inspect the table:

```bash
mysql -u root -p -e "DESCRIBE employeedb.employees;"
```

## Database Configuration

The default database settings are in `src/main/java/com/learn/employee/config/PersistenceConfig.java`:

```java
dataSource.setUrl("jdbc:mysql://localhost:3306/employeedb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
dataSource.setUsername("root");
dataSource.setPassword("admin");
```

Change `setUsername` and `setPassword` if your local MySQL account uses different credentials.

The `allowPublicKeyRetrieval=true` option is included because newer MySQL versions may otherwise reject JDBC login with `Public Key Retrieval is not allowed`.

## Build

From the project folder:

```bash
cd /Users/bishalw/Documents/Assignment/EmployeeMVC
mvn clean package
```

The WAR file will be created at:

```text
target/EmployeeMVC.war
```

## Deploy On Tomcat 9

Copy the WAR to Tomcat 9:

```bash
cp target/EmployeeMVC.war /opt/homebrew/opt/tomcat@9/libexec/webapps/EmployeeMVC.war
```

Start Tomcat 9:

```bash
/opt/homebrew/opt/tomcat@9/bin/catalina run
```

Open the app:

```text
http://localhost:8080/EmployeeMVC/
```

If Tomcat is already running, copying a newly built WAR into `webapps` should trigger an automatic redeploy.

## Project Structure

```text
src/main/java/com/learn/employee
├── config
├── controller
├── exception
├── model
├── repository
└── service

src/main/webapp/WEB-INF/views/employees
├── add.jsp
├── edit.jsp
├── error.jsp
├── list.jsp
└── view.jsp
```

## Troubleshooting

- `HTTP Status 404` for `/EmployeeMVC/`: the WAR did not deploy or the Spring context failed during startup. Check the Tomcat console logs.
- `Access denied for user 'root'@'localhost'`: update the MySQL username or password in `PersistenceConfig.java`, rebuild, and redeploy.
- `Public Key Retrieval is not allowed`: keep `allowPublicKeyRetrieval=true` in the JDBC URL.
- `Unknown database 'employeedb'`: run `database/employeemvc_schema.sql`.
