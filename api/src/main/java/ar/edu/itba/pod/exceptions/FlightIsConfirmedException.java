package ar.edu.itba.pod.exceptions;

public class FlightIsConfirmedException extends IllegalArgumentException{
    public FlightIsConfirmedException(){
        super("FlightIsConfirmedException");
    }
}
