package com.learn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.learn.model.Booking;

public class BookingDAO {

    public int insertBooking(Connection connection, Booking booking) throws SQLException {
        String sql = "INSERT INTO bookings "
                + "(user_id, flight_id, passenger_name, passenger_email, seats_booked, booking_status, created_at) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, booking.getUserId());
            preparedStatement.setInt(2, booking.getFlightId());
            preparedStatement.setString(3, booking.getPassengerName());
            preparedStatement.setString(4, booking.getPassengerEmail());
            preparedStatement.setInt(5, booking.getSeatsBooked());
            preparedStatement.setString(6, booking.getBookingStatus());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(booking.getCreatedAt()));
            return preparedStatement.executeUpdate();
        }
    }
}
