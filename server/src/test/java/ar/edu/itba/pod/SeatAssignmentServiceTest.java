package ar.edu.itba.pod;

import ar.edu.itba.pod.server.SeatAssignmentService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SeatAssignmentServiceTest {
    SeatAssignmentService seatAssignmentService;
    @Test
    public void AssignSeat_WithWeirdFlighCode_ShouldThrowFlightDoesNotExist(){

//        Arrange

        seatAssignmentService = new SeatAssignmentService();
//        Act
        try {
            seatAssignmentService.assignSeat("23232", "Lucia", 1, 'a');
//            Assert
            fail();
        }catch (Exception e) {
            assertEquals("java.lang.IllegalArgumentException: FlightDoesNotExistException", e.getMessage());
        }
    }

    @Test
    public void AssignSeat_ShouldThrowFlightDoesNotExist(){

//        Arrange

        seatAssignmentService = new SeatAssignmentService();
//        Act
        try {
            seatAssignmentService.assignSeat("23232", "Lucia", 1, 'a');
//            Assert
            fail();
        }catch (Exception e) {
            assertEquals("java.lang.IllegalArgumentException: FlightDoesNotExistException", e.getMessage());
        }
    }




}
