package ar.edu.itba.pod.exceptions;

public class PassengerAlreadyAssignedException extends IllegalArgumentException{
    public PassengerAlreadyAssignedException(){
        super("PassengerAlreadyAssignedException");
    }
}
