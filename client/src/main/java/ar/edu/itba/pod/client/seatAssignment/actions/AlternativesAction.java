package ar.edu.itba.pod.client.seatAssignment.actions;

import ar.edu.itba.pod.client.seatAssignment.CliParser;
import ar.edu.itba.pod.services.SeatAssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlternativesAction implements Runnable {
    private final Logger logger;
    private final SeatAssignmentService service;
    private final CliParser.Arguments arguments;

    public AlternativesAction(SeatAssignmentService service, CliParser.Arguments arguments) {
        this.service = service;
        this.arguments = arguments;
        this.logger = LoggerFactory.getLogger(AlternativesAction.class);
    }

    @Override
    public void run() {
        var flightCode = arguments.getFlightCode();
        try {
            var alternativeFlights = service.getAlternativeFlights(flightCode, arguments.getPassengerName());
            alternativeFlights.forEach(flight -> {
                logger.info("{} | {} | {} {}", flight.getFlight().getAirportCode(), flight.getFlight().getFlightCode(), flight.getFreeSeats(), flight.getCategory());
            });
        } catch (Exception e) {
            logger.error("Cannot get alternatives for flight {}", flightCode);
        }
    }
}
