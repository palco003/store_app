package cen4270.exceptions;

public class CannotSendEmailException extends Exception {
    public CannotSendEmailException(String msg) {
        super(msg);
    }
}
