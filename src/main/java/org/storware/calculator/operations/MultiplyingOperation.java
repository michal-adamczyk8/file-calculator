package org.storware.calculator.operations;

import lombok.Getter;
import org.storware.calculator.annotation.Operator;

@Getter
@Operator
public class MultiplyingOperation implements Operation {

    private final String operatorName = "multiply";

    @Override
    public Double apply(Double result, Double number) {
        return result * number;
    }


}
