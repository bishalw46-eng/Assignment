package com.learn.model;

public class BookingResult {

    private final boolean success;
    private final String message;
    private final Integer remainingSeats;
    private final String flightNumber;

    private BookingResult(boolean success, String message, Integer remainingSeats, String flightNumber) {
        this.success = success;
        this.message = message;
        this.remainingSeats = remainingSeats;
        this.flightNumber = flightNumber;
    }

    public static BookingResult success(String message, int remainingSeats, String flightNumber) {
        return new BookingResult(true, message, remainingSeats, flightNumber);
    }

    public static BookingResult failure(String message) {
        return new BookingResult(false, message, null, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Integer getRemainingSeats() {
        return remainingSeats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }
}
