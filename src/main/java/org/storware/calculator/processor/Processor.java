package org.storware.calculator.processor;


import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.storware.calculator.contant.Constants;
import org.storware.calculator.exceptions.IncorrectLineFormatException;
import org.storware.calculator.exceptions.IncorrectOperandException;
import org.storware.calculator.exceptions.IncorrectOperatorException;
import org.storware.calculator.exceptions.NoApplyOperatorException;
import org.storware.calculator.exceptions.NoLinesProvidedException;
import org.storware.calculator.instruction.Instruction;
import org.storware.calculator.operations.Operation;
import org.storware.calculator.output.Result;
import org.storware.calculator.reflection.ReflectionUtils;
import org.storware.calculator.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class Processor {

    private final ReflectionUtils reflectionUtils;

    private static Logger log = LogManager.getLogger(Processor.class);
    /**
     * Main method of the application responsible for processing lines from the file.
     *
     * @param valuesFromFile
     */
    public Result process(List<String> valuesFromFile)
            throws IncorrectOperandException, IncorrectOperatorException, NoApplyOperatorException, NoLinesProvidedException,
            IncorrectLineFormatException {
        Validator.validateIfLinesAreNotEmpty(valuesFromFile);
        List<Instruction> instructions = new ArrayList<>();
        Map<String, Operation> operatorsMap = reflectionUtils.getOperatorNames();
        Double startNumber = null;
        int lineCounter = 0;
        for (String line : valuesFromFile) {
            lineCounter++;
            String[] values = splitLine(line);
            if (Constants.APPLY_OPERATOR.equals(values[0])) {
                startNumber = convert(values[1]);
                break;
            }
            Validator.validateValues(values, operatorsMap, lineCounter);
            Operation operation = getOperationForOperationName(values[0], operatorsMap);
            Double number = convert(values[1]);
            instructions.add(new Instruction(number, operation));

        }
        Validator.validateStartNumber(startNumber);
        Double result = calculateResult(instructions, startNumber);
        return new Result(result);
    }

    /**
     * Method responsible for splitting line from file in two parts based on defined delimiter
     *
     * @param line
     * @return
     */
    private String[] splitLine(String line) {
        return line.split(Constants.SPLIT_DELIMITER);
    }

    /**
     * Method responsible for finding correct operation class based on provided operator name.
     *
     * @param operationName
     * @return
     */
    private Operation getOperationForOperationName(String operationName, Map<String, Operation> operatorsMap) {
        return operatorsMap.get(operationName);
    }

    /**
     * Method responsible for calculating operations on startNumber based on list of instructions provided.
     *
     * @param instructions
     * @param startNumber
     * @return
     */
    private Double calculateResult(List<Instruction> instructions, Double startNumber) {
        log.info("Calculating output...");
        for (Instruction instruction : instructions) {
            log.info("Current result: {}, nextOperation: {} {}",
                        startNumber,
                        instruction.getOperation().getOperatorName(),
                        instruction.getNumber());
            startNumber = instruction.getOperation().apply(startNumber, instruction.getNumber());
        }
        log.info("Calculating output finished.");
        return startNumber;
    }

    /**
     * Method responsible for converting string value to double.
     *
     * @param value
     * @return
     */
    private Double convert(String value) {
        return Double.valueOf(value);
    }
}
