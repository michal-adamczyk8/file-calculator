package org.storware.calculator.exceptions;

public class NoLinesProvidedException extends Exception {
    public NoLinesProvidedException() {
        super("Provided file didn't contain any data!");
    }
}
