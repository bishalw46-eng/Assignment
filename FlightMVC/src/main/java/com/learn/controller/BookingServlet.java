package com.learn.controller;

import java.io.IOException;
import java.util.List;

import com.learn.model.BookingResult;
import com.learn.model.Flight;
import com.learn.service.FlightBookingService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final FlightBookingService flightBookingService = new FlightBookingService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        forwardToBookingPage(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int userId = session != null && session.getAttribute("userId") instanceof Integer
                ? (Integer) session.getAttribute("userId")
                : -1;

        int flightId;
        int seatsRequested;
        try {
            flightId = Integer.parseInt(request.getParameter("flightId"));
            seatsRequested = Integer.parseInt(request.getParameter("seatsRequested"));
        } catch (NumberFormatException exception) {
            request.setAttribute("error", "Flight and seats must be valid numbers.");
            preserveFormValues(request);
            forwardToBookingPage(request, response);
            return;
        }

        BookingResult result = flightBookingService.bookFlight(
                userId,
                flightId,
                request.getParameter("passengerName"),
                request.getParameter("passengerEmail"),
                seatsRequested
        );

        preserveFormValues(request);
        request.setAttribute("selectedFlightId", flightId);
        if (result.isSuccess()) {
            request.setAttribute("success", result.getMessage());
            request.setAttribute("remainingSeats", result.getRemainingSeats());
            request.setAttribute("confirmedFlightNumber", result.getFlightNumber());
            clearSubmittedValues(request);
        } else {
            request.setAttribute("error", result.getMessage());
        }

        forwardToBookingPage(request, response);
    }

    private void forwardToBookingPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Flight> flights = flightBookingService.getAvailableFlights();
        request.setAttribute("flights", flights);
        request.getRequestDispatcher("/booking.jsp").forward(request, response);
    }

    private void preserveFormValues(HttpServletRequest request) {
        request.setAttribute("submittedPassengerName", request.getParameter("passengerName"));
        request.setAttribute("submittedPassengerEmail", request.getParameter("passengerEmail"));
        request.setAttribute("submittedSeatsRequested", request.getParameter("seatsRequested"));
    }

    private void clearSubmittedValues(HttpServletRequest request) {
        request.setAttribute("submittedPassengerName", "");
        request.setAttribute("submittedPassengerEmail", "");
        request.setAttribute("submittedSeatsRequested", "");
    }
}
