package org.storware.calculator.exceptions;

public class NoApplyOperatorException extends Exception {
    public NoApplyOperatorException() {
        super("No apply operator was provided in the file");
    }
}
