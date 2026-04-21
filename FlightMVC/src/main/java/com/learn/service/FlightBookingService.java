package com.learn.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.learn.dao.BookingDAO;
import com.learn.dao.FlightDAO;
import com.learn.model.Booking;
import com.learn.model.BookingResult;
import com.learn.model.Flight;
import com.learn.util.DatabaseUtil;

public class FlightBookingService {

    private final FlightDAO flightDAO;
    private final BookingDAO bookingDAO;

    public FlightBookingService() {
        this.flightDAO = new FlightDAO();
        this.bookingDAO = new BookingDAO();
    }

    public List<Flight> getAvailableFlights() {
        try {
            return flightDAO.getAllFlights();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return new ArrayList<>();
        }
    }

    public BookingResult bookFlight(int userId, int flightId, String passengerName, String passengerEmail, int seatsRequested) {
        String validationError = validateBookingInput(userId, flightId, passengerName, passengerEmail, seatsRequested);
        if (validationError != null) {
            return BookingResult.failure(validationError);
        }

        try (Connection connection = DatabaseUtil.getConnection()) {
            connection.setAutoCommit(false);

            try {
                Flight flight = flightDAO.getFlightById(connection, flightId, true);
                if (flight == null) {
                    connection.rollback();
                    return BookingResult.failure("Selected flight was not found.");
                }

                if (flight.getAvailableSeats() <= 0) {
                    connection.rollback();
                    return BookingResult.failure("Flight fully booked. Please choose another flight.");
                }

                if (seatsRequested > flight.getAvailableSeats()) {
                    connection.rollback();
                    return BookingResult.failure("Only " + flight.getAvailableSeats() + " seats available for flight "
                            + flight.getFlightNumber() + ".");
                }

                Booking booking = new Booking();
                booking.setUserId(userId);
                booking.setFlightId(flightId);
                booking.setPassengerName(passengerName.trim());
                booking.setPassengerEmail(passengerEmail.trim());
                booking.setSeatsBooked(seatsRequested);
                booking.setBookingStatus("CONFIRMED");
                booking.setCreatedAt(LocalDateTime.now());

                bookingDAO.insertBooking(connection, booking);

                int remainingSeats = flight.getAvailableSeats() - seatsRequested;
                flightDAO.updateAvailableSeats(connection, flightId, remainingSeats);

                connection.commit();
                return BookingResult.success(
                        "Booking confirmed for flight " + flight.getFlightNumber() + ".",
                        remainingSeats,
                        flight.getFlightNumber()
                );
            } catch (SQLException exception) {
                connection.rollback();
                exception.printStackTrace();
                return BookingResult.failure("Unable to complete booking right now.");
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            return BookingResult.failure("Unable to connect to the database.");
        }
    }

    private String validateBookingInput(int userId, int flightId, String passengerName, String passengerEmail, int seatsRequested) {
        if (userId <= 0) {
            return "Please login again before booking.";
        }
        if (flightId <= 0) {
            return "Please select a flight.";
        }
        if (passengerName == null || passengerName.trim().isEmpty()) {
            return "Passenger name is required.";
        }
        if (passengerEmail == null || passengerEmail.trim().isEmpty()) {
            return "Passenger email is required.";
        }
        if (!passengerEmail.contains("@")) {
            return "Enter a valid passenger email address.";
        }
        if (seatsRequested <= 0) {
            return "Seats requested must be greater than zero.";
        }
        return null;
    }
}
