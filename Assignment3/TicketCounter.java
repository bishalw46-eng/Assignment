package Assignment3;

public class TicketCounter {
    private int availableSeats = 10;

    public synchronized boolean bookSeat(String counterName) {
        if (availableSeats > 0) {
            availableSeats--;
            System.out.println(counterName + " booked a seat. Remaining: " + availableSeats);
            return true;
        }

        System.out.println(counterName + ": No seats available!");
        return false;
    }

    public synchronized int getAvailableSeats() {
        return availableSeats;
    }
}
