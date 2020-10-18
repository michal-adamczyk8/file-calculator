package org.storware.calculator.operations;


import lombok.Getter;
import org.storware.calculator.annotation.Operator;

@Getter
@Operator
public class AddingOperation implements Operation {

    private final String operatorName = "add";

    @Override
    public Double apply(Double result, Double number) {
        return result + number;
    }
}
