package net.javaguides.springboot.exception;

public class ConsumerNotFoundException extends Throwable {
    public ConsumerNotFoundException(String message) {
        super(message);
    }
}
