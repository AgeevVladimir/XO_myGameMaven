package model.exceptions;

public abstract class AbstractXOException extends Exception {
    public AbstractXOException() {
        super();
    }

    public AbstractXOException(String message) {
        super(message);
    }
}
