package ar.edu.itba.pod.models;

public class Ticket {
    private Seat seat;
    private final String flightCode;
    private final String passengerName;
    private final SeatCategory seatCategory;

    public Ticket(String flightCode, String passengerName, SeatCategory seatCategory) {
        this.flightCode = flightCode;
        this.passengerName = passengerName;
        this.seatCategory = seatCategory;
        this.seat = null;
    }

    public Ticket(ar.edu.itba.pod.models.Seat seat, String flightCode, String passengerName, SeatCategory seatCategory) {
        this.seat = seat;
        this.flightCode = flightCode;
        this.passengerName = passengerName;
        this.seatCategory = seatCategory;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public SeatCategory getCategory() {
        return seatCategory;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(ar.edu.itba.pod.models.Seat seat) {
        this.seat = seat;
    }
}
