package ar.edu.itba.pod.server.notifications;

import ar.edu.itba.pod.interfaces.PassengerNotifier;
import ar.edu.itba.pod.models.Flight;
import ar.edu.itba.pod.models.SeatCategory;
import ar.edu.itba.pod.models.Ticket;

import java.rmi.RemoteException;

public interface EventsManager {
    void addPassengerSubscriber(String passengerName, String flightCode, String destination, PassengerNotifier passengerNotifier) throws RemoteException;

    void notifySeatAssignment(String passengerName, String flightCode, String destination, Ticket.SeatLocation seat, SeatCategory category) throws RemoteException;

    void notifySeatChange(String passengerName, String flightCode, String destination, Ticket.SeatLocation oldSeat, SeatCategory oldCategory, Ticket.SeatLocation newSeat, SeatCategory newCategory) throws RemoteException;

    void notifyFlightCancellation(Flight flight) throws RemoteException;

    void notifyFlightChange(Flight oldFlight, Flight newFlight, String passengerName) throws RemoteException;

    void notifyFlightConfirmation(Flight flight) throws RemoteException;
}
