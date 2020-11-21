package model.exceptions;

public class AlreadyOccupiedException extends AbstractXOException{
    public AlreadyOccupiedException() {
        super();
    }
    public AlreadyOccupiedException(String message) {
        super(message);
    }
}
