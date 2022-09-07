package ar.edu.itba.pod.server;

import ar.edu.itba.pod.models.*;
import ar.edu.itba.pod.server.exceptions.*;
import ar.edu.itba.pod.utils.Pair;

import java.util.List;
import java.util.Optional;

public class SeatAssignmentService implements ar.edu.itba.pod.services.SeatAssignmentService {

    List<Flight> flightList;
//    todo levatar la clase de storage en vez de tener el list de flights

    @Override
    public Boolean isSeatTaken(String flightCode, int row, char column) {
        return null;
    }
//todo hacer excepciones como clases
    @Override
    public void assignSeat(String flightCode, String passenger, int row, char column) throws Exception {
        if(passenger.isEmpty())
            throw  new InvalidPassengerException();

        if(row <= 0 || !Character.isLetter(column))
            throw new SeatDoesNotExistException();

        Flight flight = flightList.stream()
                .filter(f -> f.getFlightCode().equals(flightCode))
                .findFirst().orElseThrow(FlightDoesNotExistException::new);

        if(flight.getStatus() == FlightStatus.CONFIRMED)
            throw new FlightIsConfirmedException();

        Ticket ticket = flight.getTickets().stream()
                .filter(t -> t.getPassengerName().equals(passenger))
                .findAny().orElseThrow(InvalidPassengerException::new);

        if(ticket.getSeat() != null)
            throw new PassengerAlreadyAssignedException();

        Optional<Ticket> ticketOnLocation = flight.getTickets().stream()
                .filter(t -> t.getSeat().getLocation().equals(new Pair<>(row, column)))
                .findAny();
        if(ticketOnLocation.isPresent())
            throw new SeatAlreadyAssignedException();

        //       todo compare categories
//            throw new RuntimeException("SeatCategoryIsToHighException");
//        fixme get seat category
        Seat seat = new Seat(true, passenger,row, column, SeatCategory.BUSINESS );
        ticket.setSeat(seat);

    }

    @Override
    public void changeSeat(String flightCode, String passenger, int row, char column) {

    }

    @Override
    public List<Flight> getAlternativeFlights(String flightCode, String passenger) {
        return null;
    }

    @Override
    public void changeTicket(String passenger, String flightCode, String newFlightCode) {

    }
}
