package ar.edu.itba.pod.client.seatAssignment.actions;

import ar.edu.itba.pod.client.seatAssignment.CliParser;
import ar.edu.itba.pod.services.SeatAssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatusAction implements Runnable {
    private final Logger logger;
    private final SeatAssignmentService service;
    private final CliParser.Arguments arguments;
    public StatusAction(SeatAssignmentService service, CliParser.Arguments arguments) {
        this.service = service;
        this.arguments = arguments;
        this.logger = LoggerFactory.getLogger(StatusAction.class);
    }

    @Override
    public void run() {
        var flightCode = arguments.getFlightCode();
        try {
            var status = service.isSeatTaken(flightCode, arguments.getRow(), arguments.getCol().charAt(0));
            logger.info("Seat {}{} is {}.", arguments.getRow(), arguments.getCol(), status ? "TAKEN" : "FREE");
        } catch (Exception e) {
            logger.error("Cannot get the status of flight {}", flightCode);
        }
    }
}
