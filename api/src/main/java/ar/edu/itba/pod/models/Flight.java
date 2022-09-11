package ar.edu.itba.pod.models;

import ar.edu.itba.pod.exceptions.PassengerNotExistException;
import ar.edu.itba.pod.utils.Pair;
import lombok.Getter;

import java.io.Serializable;
import java.util.*;

public class Flight implements Serializable {
    private FlightStatus status;
    @Getter
    private final String airportCode;
    @Getter
    private final String flightCode;
    @Getter
    private final Plane plane;
    @Getter
    private final List<Ticket> tickets;

    public Flight(FlightStatus status, String airportCode, String flightCode, Plane plane, List<Ticket> tickets) {
        this.status = status;
        this.airportCode = airportCode;
        this.flightCode = flightCode;
        this.plane = plane;
        this.tickets = Collections.synchronizedList(tickets);
    }

    public SeatCategory getMaxCategoryAvailable(Ticket ticket) {
        SeatCategory maxCategory = ticket.getSeatCategory();

        Map<SeatCategory, Integer> seats = new TreeMap<>();
        for (SeatCategory category : SeatCategory.values()) {
            if (category.compareTo(maxCategory) >= 0) {
                seats.put(category, plane.getSeatsDistribution().getOrDefault(category, 0));
            }
        }

        synchronized (tickets) {
            this.tickets.stream()
                    .filter(t -> t.getSeatLocation().isPresent())
                    .forEach(t -> seats.put(t.getSeatCategory(), seats.get(t.getSeatCategory()) - 1));
        }

        for (SeatCategory category : SeatCategory.values()) {
            if (seats.getOrDefault(category, 0) > 0) {
                return category;
            }
        }
        return null;
    }

    public int getFreeSeats(SeatCategory maxCategory) {
        int freeSeats = 0;
        for (SeatCategory category : SeatCategory.values()) {
            if (category.compareTo(maxCategory) >= 0) {
                freeSeats += plane.getSeatsDistribution().get(category);
            }
        }

        synchronized (tickets) {
            freeSeats -= tickets.stream()
                    .filter(t -> t.getSeatLocation().isPresent())
                    .filter(t -> t.getSeatCategory().compareTo(maxCategory) >= 0)
                    .count();
        }

        return freeSeats;
    }
    private int getFreeSeatsInCategory(SeatCategory category) {
        int freeSeats = 0;
        for (SeatCategory c : SeatCategory.values()) {
            if (c.compareTo(category) == 0) {
                freeSeats += plane.getSeatsDistribution().get(c);
            }
        }

        synchronized (tickets) {
            freeSeats -= tickets.stream()
                    .filter(t -> t.getSeatLocation().isPresent())
                    .filter(t -> t.getSeatCategory().compareTo(category) == 0)
                    .count();
        }

        return freeSeats;
    }

    public List<AlternativeFlight> getAlternativeFlights(Collection<Flight> flights, String passenger){
        Ticket ticket;
        synchronized (tickets) {
            ticket = this.getTickets().stream()
                    .findFirst()
                    .filter(t -> t.getPassengerName().equals(passenger))
                    .orElseThrow(PassengerNotExistException::new);
        }
        List<Flight> alternativeFlights = AlternativeFlight.getAlternativeFlights(flights, ticket, this);
        List<AlternativeFlight> alternativeFlights1 = new ArrayList<>();

        for (Flight alternativeFlight: alternativeFlights) {

            SeatCategory maxCategory = alternativeFlight.getMaxCategoryAvailable(ticket);
            SeatCategory[] seatCategories = SeatCategory.values();
            int index = Arrays.asList(SeatCategory.values()).indexOf(maxCategory);
            List<Pair<SeatCategory, Integer>> freeSeats = new ArrayList<>();

            for (int i = index; i<seatCategories.length; i++){
                int numFreeSeats = alternativeFlight.getFreeSeatsInCategory(seatCategories[i]);
                freeSeats.add(new Pair<>(seatCategories[i], numFreeSeats));
            }

            AlternativeFlight af = new AlternativeFlight(
                    alternativeFlight.getFlightCode(),
                    alternativeFlight.getAirportCode(),
                    freeSeats);
            alternativeFlights1.add(af);

        }
        return alternativeFlights1;
    }
    public synchronized FlightStatus getStatus() {
        return status;
    }

    public synchronized void setStatus(FlightStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Flight flight = (Flight) o;
        return flightCode.equals(flight.flightCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightCode);
    }

    public synchronized boolean isStatus(FlightStatus confirmed) {
        return this.status == confirmed;
    }
}
