package org.storware.calculator.exceptions;

public class IncorrectOperatorException extends Exception {
    public IncorrectOperatorException(Integer lineCounter) {
        super("The operator at line number: " + lineCounter + " is unknown! Please rewrite the file");
    }
}
