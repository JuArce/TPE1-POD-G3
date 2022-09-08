package ar.edu.itba.pod.services;

import ar.edu.itba.pod.exceptions.FlightDoesNotExistException;
import ar.edu.itba.pod.models.criteria.Criteria;
import ar.edu.itba.pod.models.SeatRow;

import java.util.List;

public interface SeatMapService {
    List<SeatRow> getSeatMap(String flightCode, Criteria criteria) throws FlightDoesNotExistException;
}
