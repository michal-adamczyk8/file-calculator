package org.storware.calculator.exceptions;

public class IncorrectLineFormatException extends Exception {

    public IncorrectLineFormatException(Integer linCounter) {
        super("The line from the file at number: " + linCounter+ " is incorrect, please rewrite the file");
    }
}
