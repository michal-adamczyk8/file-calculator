package org.storware.calculator.output;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Result implements IResult<Double, Void> {
    private Double result;

    public String output() {
        return "Output: " + getResult();
    }

    @Override
    public Void reportOutput() {
        System.out.println(output());
        return null;
    }
}
