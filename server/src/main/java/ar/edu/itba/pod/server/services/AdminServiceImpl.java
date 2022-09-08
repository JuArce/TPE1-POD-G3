package ar.edu.itba.pod.server.services;

import ar.edu.itba.pod.exceptions.*;
import ar.edu.itba.pod.models.*;
import ar.edu.itba.pod.services.AdminService;
import ar.edu.itba.pod.utils.Pair;

import java.rmi.RemoteException;
import java.util.List;
import java.util.TreeMap;

public class AdminServiceImpl implements AdminService {

    private final List<Plane> planes;
    private final List<Flight> flights;

    public AdminServiceImpl(List<Plane> planes, List<Flight> flights) {
        this.planes = planes;
        this.flights = flights;
    }

    @Override
    public void addPlane(String planeModelName, TreeMap<SeatCategory, Pair<Integer, Integer>> seatsPerCategory) throws RemoteException {
        if (planes.stream().anyMatch(plane -> plane.getModelName().equals(planeModelName))) {
            throw new PlaneModelAlreadyExistsException();
        }
        final Plane plane = new Plane(planeModelName, seatsPerCategory);
        planes.add(plane);
    }

    @Override
    public void addFlight(String planeModelName, String flightCode, String airportCode, List<Ticket> tickets) throws RemoteException {
        if (flights.stream().anyMatch(flight -> flight.getFlightCode().equals(flightCode))) {
            throw new FlightCodeAlreadyExistsException();
        }

        Plane plane = planes.stream().filter(p -> p.getModelName().equals(planeModelName)).findFirst().orElseThrow(PlaneModelNotExistException::new);
        Flight flight = new Flight(FlightStatus.SCHEDULED, airportCode, flightCode, plane, tickets);
        flights.add(flight);
    }

    @Override
    public FlightStatus getFlightStatus(String flightCode) throws RemoteException {
        Flight flight = this.flights.stream().filter(f -> f.getFlightCode().equals(flightCode)).findFirst().orElseThrow(FlightCodeNotExistException::new);
        return flight.getStatus();
    }

    @Override
    public void confirmFlight(String flightCode) throws RemoteException {
        setFlightStatus(flightCode, FlightStatus.CONFIRMED);
    }

    @Override
    public void cancelFlight(String flightCode) throws RemoteException {
        setFlightStatus(flightCode, FlightStatus.CANCELLED);
    }

    private void setFlightStatus(String flightCode, FlightStatus status) {
        Flight flight = this.flights.stream().filter(f -> f.getFlightCode().equals(flightCode)).findFirst().orElseThrow(FlightCodeNotExistException::new);
        if(!flight.getStatus().equals(FlightStatus.SCHEDULED)) {
            throw new FlightStatusNotPendingException();
        }
        flight.setStatus(status);
    }

    @Override
    public ReticketingReport rescheduleTickets() throws RemoteException {
        return null;
    }
}
