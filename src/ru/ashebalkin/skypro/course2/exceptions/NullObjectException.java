package ru.ashebalkin.skypro.course2.exceptions;

public class NullObjectException extends RuntimeException {
    public NullObjectException() {
        super("Переданн пустой обхект");
    }

    public NullObjectException(String message) {
        super(message);
    }

    public NullObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullObjectException(Throwable cause) {
        super(cause);
    }
}
