CREATE DATABASE IF NOT EXISTS flightdb;
USE flightdb;

CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS flights (
    id INT PRIMARY KEY AUTO_INCREMENT,
    flight_number VARCHAR(20) NOT NULL UNIQUE,
    source VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    travel_date DATE NOT NULL,
    available_seats INT NOT NULL
);

CREATE TABLE IF NOT EXISTS bookings (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    flight_id INT NOT NULL,
    passenger_name VARCHAR(100) NOT NULL,
    passenger_email VARCHAR(150) NOT NULL,
    seats_booked INT NOT NULL,
    booking_status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_bookings_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_bookings_flight FOREIGN KEY (flight_id) REFERENCES flights(id)
);

INSERT INTO users (username, password)
VALUES ('admin', 'admin123')
ON DUPLICATE KEY UPDATE password = VALUES(password);

INSERT INTO flights (flight_number, source, destination, travel_date, available_seats)
VALUES
    ('AI101', 'New York', 'Los Angeles', '2026-05-10', 5),
    ('AI205', 'Chicago', 'Dallas', '2026-05-12', 3),
    ('AI309', 'Seattle', 'San Francisco', '2026-05-14', 2)
ON DUPLICATE KEY UPDATE
    source = VALUES(source),
    destination = VALUES(destination),
    travel_date = VALUES(travel_date),
    available_seats = VALUES(available_seats);
