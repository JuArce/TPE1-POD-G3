package ar.edu.itba.pod.client.admin.actions;

import ar.edu.itba.pod.client.admin.CliParser;
import ar.edu.itba.pod.services.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightsAction {
    private final AdminService service;
    private final CliParser.Arguments arguments;
    private final Logger logger;
    public FlightsAction(AdminService service, CliParser.Arguments arguments) {
        logger = LoggerFactory.getLogger(FlightsAction.class);
        this.service = service;
        this.arguments = arguments;
    }

    public FlightsAction(AdminService service, CliParser.Arguments arguments, Logger logger) {
        this.service = service;
        this.arguments = arguments;
        this.logger = logger;
    }

    public void run() {

    }
}
