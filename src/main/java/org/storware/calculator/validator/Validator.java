package org.storware.calculator.validator;

import org.apache.commons.lang3.math.NumberUtils;
import org.storware.calculator.exceptions.IncorrectLineFormatException;
import org.storware.calculator.exceptions.IncorrectOperandException;
import org.storware.calculator.exceptions.IncorrectOperatorException;
import org.storware.calculator.exceptions.NoApplyOperatorException;
import org.storware.calculator.exceptions.NoLinesProvidedException;
import org.storware.calculator.operations.Operation;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Validator {

    public static boolean validateIfLinesAreNotEmpty(List<String> lines) throws NoLinesProvidedException {
        if (lines.isEmpty()) {
            throw new NoLinesProvidedException();
        }
        return true;
    }

    /**
     * Method resposible for validating values gotten from line of file. IncorrectOperatorException is thrown when provided
     * operator is unknown. IncorrectOperandException is thrown when provided operand in null or not a number.
     *
     * @param value
     * @param operatorNames
     * @param lineCounter
     * @return
     * @throws IncorrectOperatorException
     * @throws IncorrectOperandException
     */
    public static boolean validateValues(String[] value, Map<String, Operation> operatorNames, Integer lineCounter)
            throws IncorrectOperatorException, IncorrectOperandException, IncorrectLineFormatException {
        if (value.length != 2) {
            throw new IncorrectLineFormatException(lineCounter);
        }
        if (!operatorNames.containsKey(value[0])) {
            throw new IncorrectOperatorException(lineCounter);
        }
        if (Objects.isNull(value[1]) || !NumberUtils.isCreatable(value[1])) {
            throw new IncorrectOperandException(lineCounter);
        }
        return true;
    }

    /**
     * Method responsible for validating number which is the first number in the chain of operations
     * that need to be done when processing provided file. StartNumber is recognised based on 'apply' operator which precedes the number.
     * If startNumber is null then NoApplyOperatorException is thrown.
     *
     * @param startNumber
     * @return
     * @throws NoApplyOperatorException
     */
    public static boolean validateStartNumber(Double startNumber) throws NoApplyOperatorException {
        if (Objects.isNull(startNumber)) {
            throw new NoApplyOperatorException();
        }
        return true;
    }
}
