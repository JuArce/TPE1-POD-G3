package ar.edu.itba.pod.server.services;

import ar.edu.itba.pod.interfaces.PassengerNotifier;
import ar.edu.itba.pod.models.SeatCategory;
import ar.edu.itba.pod.models.Ticket;
import ar.edu.itba.pod.services.NotificationService;

import java.rmi.RemoteException;

public class NotificationServiceImpl implements NotificationService {



    @Override
    public void registerPassenger(String flightCode, String passenger, PassengerNotifier remote) throws RemoteException {
        remote.notifyFlightCancellation("123", "Buenos Aires", new Ticket.SeatLocation(1, 'a'), SeatCategory.ECONOMY);
    }
}
