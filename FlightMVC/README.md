# FlightMVC

Classic Servlet/JSP MVC flight-booking assignment using MySQL and Maven.

## Build

```bash
mvn -q -DskipTests package
```

## App Flow

- Login first
- Open booking page
- Select a predefined flight
- Request seats
- See booking confirmation or a fully-booked/availability message

## Default Database Settings

- Database: `flightdb`
- User: `root`
- Password: `admin`

## Default Login

- Username: `admin`
- Password: `admin123`

## Database Setup

Run:

```sql
source database/flightmvc_schema.sql;
```
