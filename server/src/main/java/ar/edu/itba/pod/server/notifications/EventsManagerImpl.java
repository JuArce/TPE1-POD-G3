package ar.edu.itba.pod.server.notifications;

import ar.edu.itba.pod.exceptions.PassengerNotExistException;
import ar.edu.itba.pod.interfaces.PassengerNotifier;
import ar.edu.itba.pod.models.Flight;
import ar.edu.itba.pod.models.SeatCategory;
import ar.edu.itba.pod.models.Ticket;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class EventsManagerImpl implements EventsManager {

    private final List<PassengerSubscriber> passengerSubscribers;

    public EventsManagerImpl() {
        this.passengerSubscribers = new ArrayList<>();
    }

    @Override
    public void addPassengerSubscriber(String passengerName, String flightCode, String destination, PassengerNotifier passengerNotifier) throws RemoteException {
        passengerSubscribers.add(new PassengerSubscriber(passengerName, flightCode, passengerNotifier));
        passengerNotifier.notifySuccessfulRegistration(flightCode, destination);
    }

    @Override
    public void notifySeatAssignment(String passengerName, String flightCode, String destination, Ticket.SeatLocation seat, SeatCategory category) throws RemoteException {
        for (PassengerSubscriber p : this.passengerSubscribers) {
            if (p.getPassengerName().equals(passengerName) && p.getFlightCode().equals(flightCode)) {
                p.getPassengerNotifier().notifySeatAssignment(flightCode, destination, seat, category);
            }
        }
    }

    @Override
    public void notifySeatChange(String passengerName, String flightCode, String destination, Ticket.SeatLocation oldSeat, SeatCategory oldCategory, Ticket.SeatLocation newSeat, SeatCategory newCategory) throws RemoteException {
        for (PassengerSubscriber p : this.passengerSubscribers) {
            if (p.getPassengerName().equals(passengerName) && p.getFlightCode().equals(flightCode)) {
                p.getPassengerNotifier().notifySeatChange(flightCode, destination, oldSeat, oldCategory, newSeat, newCategory);
            }
        }
    }

    @Override
    public void notifyFlightCancellation(String passengerName, String flightCode, String destination, Ticket.SeatLocation seat, SeatCategory category) throws RemoteException {
        for (PassengerSubscriber p : this.passengerSubscribers) {
            if (p.getPassengerName().equals(passengerName) && p.getFlightCode().equals(flightCode)) {
                p.getPassengerNotifier().notifyFlightCancellation(flightCode, destination, seat, category);
            }
        }
    }

    @Override
    public void notifyFlightChange(String passengerName, String oldFlightCode, String newFlightCode, String destination) throws RemoteException {
        for (PassengerSubscriber p : this.passengerSubscribers) {
            if (p.getPassengerName().equals(passengerName) && p.getFlightCode().equals(oldFlightCode)) {
                p.getPassengerNotifier().notifyFlightChange(oldFlightCode, newFlightCode, destination);
            }
        }
    }

    @Override
    public void notifyFlightConfirmation(Flight flight) throws RemoteException {
        for (PassengerSubscriber p : this.passengerSubscribers) {
            if (p.getFlightCode().equals(flight.getFlightCode())) {
                Ticket ticket = flight.getTickets().stream().filter(t -> t.getPassengerName().equals(p.getPassengerName())).findFirst().orElseThrow(PassengerNotExistException::new);
                p.getPassengerNotifier().notifyFlightConfirmation(flight.getFlightCode(), flight.getAirportCode(), ticket.getSeatLocation().orElse(null), ticket.getSeatCategory());
            }
        }
    }
}
