package ar.edu.itba.pod.client.seatAssignment.actions;

import ar.edu.itba.pod.client.seatAssignment.CliParser;
import ar.edu.itba.pod.services.SeatAssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoveAction implements Runnable {
    private final Logger logger;
    private final SeatAssignmentService service;
    private final CliParser.Arguments arguments;

    public MoveAction(SeatAssignmentService service, CliParser.Arguments arguments) {
        this.service = service;
        this.arguments = arguments;
        this.logger = LoggerFactory.getLogger(MoveAction.class);
    }

    @Override
    public void run() {
        var flightCode = arguments.getFlightCode();
        try {
            service.changeSeat(flightCode, arguments.getPassengerName(), arguments.getRow(), arguments.getCol().charAt(0));
            logger.info("Seat changed successfully.");
        } catch (Exception e) {
            logger.error("Cannot move passenger from flight {}", flightCode);
        }
    }
}