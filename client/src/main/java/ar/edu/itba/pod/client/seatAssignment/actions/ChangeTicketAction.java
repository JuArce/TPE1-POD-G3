package ar.edu.itba.pod.client.seatAssignment.actions;

import ar.edu.itba.pod.client.seatAssignment.CliParser;
import ar.edu.itba.pod.services.SeatAssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChangeTicketAction implements Runnable {
    private final Logger logger;
    private final SeatAssignmentService service;
    private final CliParser.Arguments arguments;

    public ChangeTicketAction(SeatAssignmentService service, CliParser.Arguments arguments) {
        this.service = service;
        this.arguments = arguments;
        this.logger = LoggerFactory.getLogger(ChangeTicketAction.class);
    }

    @Override
    public void run() {
        // TODO: Implementar
    }
}
