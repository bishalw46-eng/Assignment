<%@page import="com.learn.model.Flight"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flight Booking</title>
</head>
<body>
<%
    List<Flight> flights = (List<Flight>) request.getAttribute("flights");
    String username = (String) session.getAttribute("username");
    String error = (String) request.getAttribute("error");
    String success = (String) request.getAttribute("success");
    Integer remainingSeats = (Integer) request.getAttribute("remainingSeats");
    Integer selectedFlightId = (Integer) request.getAttribute("selectedFlightId");
    String submittedPassengerName = (String) request.getAttribute("submittedPassengerName");
    String submittedPassengerEmail = (String) request.getAttribute("submittedPassengerEmail");
    Object submittedSeatsRequested = request.getAttribute("submittedSeatsRequested");
%>

    <h2>Flight Booking Portal</h2>
    <p>Welcome, <strong><%= username %></strong></p>
    <p><a href="${pageContext.request.contextPath}/logout">Logout</a></p>

<% if (success != null) { %>
    <p style="color:green;"><%= success %>
    <% if (remainingSeats != null) { %>
        Remaining seats: <%= remainingSeats %>
    <% } %>
    </p>
<% } %>

<% if (error != null) { %>
    <p style="color:red;"><%= error %></p>
<% } %>

<% if (flights == null || flights.isEmpty()) { %>
    <p>No flights available right now. Seed the flights table and try again.</p>
<% } else { %>
    <form action="${pageContext.request.contextPath}/booking" method="post">
        <label>Passenger Name:</label>
        <input type="text" name="passengerName" required
               value="<%= submittedPassengerName != null ? submittedPassengerName : "" %>">
        <br><br>

        <label>Passenger Email:</label>
        <input type="email" name="passengerEmail" required
               value="<%= submittedPassengerEmail != null ? submittedPassengerEmail : "" %>">
        <br><br>

        <label>Select Flight:</label>
        <select name="flightId" id="flightId" onchange="updateFlightSummary()" required>
            <option value="">-- Select a flight --</option>
<%
        for (Flight flight : flights) {
            boolean selected = selectedFlightId != null && selectedFlightId == flight.getId();
%>
            <option value="<%= flight.getId() %>"
                    data-source="<%= flight.getSource() %>"
                    data-destination="<%= flight.getDestination() %>"
                    data-travel-date="<%= flight.getTravelDate() %>"
                    data-available-seats="<%= flight.getAvailableSeats() %>"
                    <%= selected ? "selected" : "" %>>
                <%= flight.getFlightNumber() %> - <%= flight.getSource() %> to <%= flight.getDestination() %>
                on <%= flight.getTravelDate() %> (<%= flight.getAvailableSeats() %> seats left)
            </option>
<%
        }
%>
        </select>
        <br><br>

        <label>Source:</label>
        <input type="text" id="source" readonly>
        <br><br>

        <label>Destination:</label>
        <input type="text" id="destination" readonly>
        <br><br>

        <label>Travel Date:</label>
        <input type="text" id="travelDate" readonly>
        <br><br>

        <label>Available Seats:</label>
        <input type="text" id="availableSeats" readonly>
        <br><br>

        <label>Seats Requested:</label>
        <input type="number" min="1" name="seatsRequested" required
               value="<%= submittedSeatsRequested != null ? submittedSeatsRequested : "" %>">
        <br><br>

        <button type="submit">Book Flight</button>
    </form>
<% } %>

<script>
function updateFlightSummary() {
    const select = document.getElementById("flightId");
    const option = select.options[select.selectedIndex];
    document.getElementById("source").value = option ? (option.dataset.source || "") : "";
    document.getElementById("destination").value = option ? (option.dataset.destination || "") : "";
    document.getElementById("travelDate").value = option ? (option.dataset.travelDate || "") : "";
    document.getElementById("availableSeats").value = option ? (option.dataset.availableSeats || "") : "";
}

updateFlightSummary();
</script>
</body>
</html>
