package ar.edu.itba.pod.exceptions;

public class SeatDoesNotExistException extends IllegalArgumentException{
    public SeatDoesNotExistException(){
        super("SeatDoesNotExistException");
    }
}
