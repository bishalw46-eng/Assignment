package com.learn.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.learn.model.Flight;
import com.learn.util.DatabaseUtil;

public class FlightDAO {

    public List<Flight> getAllFlights() throws SQLException {
        String sql = "SELECT id, flight_number, source, destination, travel_date, available_seats "
                + "FROM flights ORDER BY travel_date, flight_number";
        List<Flight> flights = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                flights.add(mapFlight(resultSet));
            }
        }

        return flights;
    }

    public Flight getFlightById(Connection connection, int flightId, boolean lockRow) throws SQLException {
        String sql = "SELECT id, flight_number, source, destination, travel_date, available_seats "
                + "FROM flights WHERE id = ?"
                + (lockRow ? " FOR UPDATE" : "");

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, flightId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapFlight(resultSet);
                }
            }
        }

        return null;
    }

    public int updateAvailableSeats(Connection connection, int flightId, int availableSeats) throws SQLException {
        String sql = "UPDATE flights SET available_seats = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, availableSeats);
            preparedStatement.setInt(2, flightId);
            return preparedStatement.executeUpdate();
        }
    }

    private Flight mapFlight(ResultSet resultSet) throws SQLException {
        Flight flight = new Flight();
        flight.setId(resultSet.getInt("id"));
        flight.setFlightNumber(resultSet.getString("flight_number"));
        flight.setSource(resultSet.getString("source"));
        flight.setDestination(resultSet.getString("destination"));
        Date travelDate = resultSet.getDate("travel_date");
        flight.setTravelDate(travelDate != null ? travelDate.toLocalDate() : null);
        flight.setAvailableSeats(resultSet.getInt("available_seats"));
        return flight;
    }
}
