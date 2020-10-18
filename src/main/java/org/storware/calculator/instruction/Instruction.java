package org.storware.calculator.instruction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.storware.calculator.operations.Operation;

@AllArgsConstructor
@Getter
public class Instruction {
    private Double number;
    private Operation operation;
}
