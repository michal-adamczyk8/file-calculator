package org.storware.calculator.exceptions;

public class DivisionByZeroException extends Exception {
    public DivisionByZeroException() {
        super("Can't divide by 0!");
    }
}
