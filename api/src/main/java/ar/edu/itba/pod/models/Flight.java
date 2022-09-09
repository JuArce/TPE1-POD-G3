package ar.edu.itba.pod.models;

import ar.edu.itba.pod.utils.Pair;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

public class Flight {
    @Getter
    @Setter
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
        this.tickets = tickets;
    }

    public SeatCategory getMaxCategoryAvailable(Ticket ticket) {
        SeatCategory maxCategory = ticket.getSeatCategory();

        Map<SeatCategory, Integer> seats = new TreeMap<>();
        for (SeatCategory category : SeatCategory.values()) {
            if (category.compareTo(maxCategory) >= 0) {
                seats.put(category, plane.getSeatsDistribution().getOrDefault(category, 0));
            }
        }

        this.tickets.stream()
                .filter(t -> t.getSeatLocation().isPresent())
                .forEach(t -> seats.put(t.getSeatCategory(), seats.get(t.getSeatCategory()) - 1));

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
            if (category.compareTo(maxCategory) <= 0) {
                freeSeats += plane.getSeatsDistribution().get(category);
            }
        }

        freeSeats -= tickets.stream()
                .filter(t -> t.getSeatLocation().isPresent())
                .filter(t -> t.getSeatCategory().compareTo(maxCategory) <= 0)
                .count();

        return freeSeats;
    }
}
