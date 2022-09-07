package ar.edu.itba.pod.client.admin.actions;

import ar.edu.itba.pod.client.admin.CliParser;
import ar.edu.itba.pod.services.AdminService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.rmi.RemoteException;

import static org.mockito.Mockito.*;

public class ConfirmActionTest {
    private final AdminService adminService = mock(AdminService.class);
    private final Logger logger = mock(Logger.class);

    @Test
    public void WithValidFlightCode_ShouldSucceed() throws RemoteException {
        // Arrange
        var flightCode = "AA1234";
        var arguments = new CliParser.Arguments();
        arguments.setFlightCode(flightCode);

        // Act
        new ConfirmAction(adminService, arguments, logger).run();

        // Assert
        verify(adminService).confirmFlight(flightCode);
        verify(logger).info("The flight {} is confirmed", flightCode);
    }

    @Test
    public void WithInvalidFlightCode_ShouldFail() throws RemoteException {
        // Arrange
        var flightCode = "AA1234";
        var arguments = new CliParser.Arguments();
        arguments.setFlightCode(flightCode);

        doThrow(RemoteException.class).when(adminService).confirmFlight(eq(flightCode));

        // Act
        new ConfirmAction(adminService, arguments, logger).run();

        // Assert
        verify(adminService).confirmFlight(flightCode);
        verify(logger).error("Error confirming flight {}", flightCode);
    }
}
