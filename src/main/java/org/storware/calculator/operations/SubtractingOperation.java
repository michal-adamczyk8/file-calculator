package org.storware.calculator.operations;

import lombok.Getter;
import org.storware.calculator.annotation.Operator;

@Getter
@Operator
public class SubtractingOperation implements Operation {

    private final String operatorName = "subtract";

    @Override
    public Double apply(Double result, Double number) {
        return result - number;
    }

    @Override
    public String getOperatorName() {
        return "subtract";
    }
}
