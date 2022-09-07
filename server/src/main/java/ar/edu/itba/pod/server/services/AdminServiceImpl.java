package ar.edu.itba.pod.server.services;

import ar.edu.itba.pod.models.FlightStatus;
import ar.edu.itba.pod.models.SeatCategory;
import ar.edu.itba.pod.models.Ticket;
import ar.edu.itba.pod.services.AdminService;
import ar.edu.itba.pod.utils.Pair;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class AdminServiceImpl implements AdminService {

    @Override
    public void addPlane(String planeModelName, Map<SeatCategory, Pair<Integer, Integer>> seatsPerCategory) throws RemoteException {

    }

    @Override
    public void addFlight(String planeModelName, String flightCode, String airportCode, List<Ticket> tickets) throws RemoteException {

    }

    @Override
    public FlightStatus getFlightStatus(String flightCode) throws RemoteException {
        return null;
    }

    @Override
    public void confirmFlight(String flightCode) throws RemoteException {

    }

    @Override
    public void cancelFlight(String flightCode) throws RemoteException {

    }

    @Override
    public void rescheduleTickets() throws RemoteException {

    }
}
