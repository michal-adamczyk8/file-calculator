package org.storware.calculator.operations;

import lombok.Getter;
import lombok.SneakyThrows;
import org.storware.calculator.annotation.Operator;
import org.storware.calculator.exceptions.DivisionByZeroException;

@Getter
@Operator
public class DividingOperation implements Operation {

    private final String operatorName = "divide";

    @SneakyThrows
    @Override
    public Double apply(Double result, Double number) {
        if (number == 0) {
            throw new DivisionByZeroException();
        }
        return result / number;
    }
}
