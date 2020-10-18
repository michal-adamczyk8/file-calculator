package org.storware.calculator.operations;

import java.util.function.BinaryOperator;

public interface Operation extends BinaryOperator<Double> {
    String getOperatorName();
}
