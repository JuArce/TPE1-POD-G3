package ar.edu.itba.pod.client.seatAssignment.actions;

import ar.edu.itba.pod.client.seatAssignment.CliParser;
import ar.edu.itba.pod.services.SeatAssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssignAction implements Runnable {
    private final Logger logger;
    private final SeatAssignmentService service;
    private final CliParser.Arguments arguments;

    public AssignAction(SeatAssignmentService service, CliParser.Arguments arguments) {
        this.service = service;
        this.arguments = arguments;
        this.logger = LoggerFactory.getLogger(AssignAction.class);
    }

    @Override
    public void run() {
        // TODO: Implementar
    }
}