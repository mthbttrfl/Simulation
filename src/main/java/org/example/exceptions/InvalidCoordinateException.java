package org.example.exceptions;

public class InvalidCoordinateException extends RuntimeException {
    public InvalidCoordinateException(String message) {
        super(message);
    }
}
