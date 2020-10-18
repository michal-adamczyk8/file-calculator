package org.storware.calculator.exceptions;

public class IncorrectOperandException extends Exception {
    public IncorrectOperandException(Integer lineCounter) {
        super("The operand in line " + lineCounter + " is either missing or incorrect. Please rewrite the file!");
    }
}
