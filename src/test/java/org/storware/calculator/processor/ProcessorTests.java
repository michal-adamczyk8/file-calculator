package org.storware.calculator.processor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.storware.calculator.exceptions.IncorrectLineFormatException;
import org.storware.calculator.exceptions.IncorrectOperandException;
import org.storware.calculator.exceptions.IncorrectOperatorException;
import org.storware.calculator.exceptions.NoApplyOperatorException;
import org.storware.calculator.exceptions.NoLinesProvidedException;
import org.storware.calculator.operations.AddingOperation;
import org.storware.calculator.operations.DividingOperation;
import org.storware.calculator.operations.MultiplyingOperation;
import org.storware.calculator.operations.Operation;
import org.storware.calculator.operations.SubtractingOperation;
import org.storware.calculator.output.Result;
import org.storware.calculator.reflection.ReflectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ProcessorTests {
    private Processor processor;
    private ReflectionUtils mockedReflectionUtils;
    private Map<String, Operation> operationMap;

    @BeforeEach
    void setUp() {
        mockedReflectionUtils = Mockito.mock(ReflectionUtils.class);
        processor = new Processor(mockedReflectionUtils);
        operationMap = new HashMap<>();
        operationMap.put("add", new AddingOperation());
        operationMap.put("subtract", new SubtractingOperation());
        operationMap.put("multiply", new MultiplyingOperation());
        operationMap.put("divide", new DividingOperation());
        Mockito.doReturn(operationMap).when(mockedReflectionUtils).getOperatorNames();
    }


    @Test
    void testProcessWhenAllOperationsAreCorrect() throws Exception {
        //Given
        List<String> listOfLines = Arrays.asList("add 2", "multiply 3", "divide 6", "apply 10");
        //When
        Result result = processor.process(listOfLines);
        //Then
        assertEquals(6, result.getResult());
    }

    @Test
    void testProcessWhenOnlyApplyOperatorProvided() throws Exception {
        //Given
        List<String> listOfLines = Arrays.asList("apply 10");
        //When
        Result result = processor.process(listOfLines);
        //Then
        assertEquals(10, result.getResult());
    }

    @Test
    void testProcessWhenNoOperatorsProvided() {
        //Given
        List<String> listOfLines = Collections.emptyList();
        //When
        Exception exception = assertThrows(NoLinesProvidedException.class, () -> processor.process(listOfLines));
        //Then
        String expectedMessage = "Provided file didn't contain any data!";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testProcessWhenNoApplyOperatorProvided() {
        //Given
        List<String> listOfLines = Arrays.asList("add 2", "multiply 3", "divide 6", "divide 10");

        //When
        Exception exception = assertThrows(NoApplyOperatorException.class, () -> processor.process(listOfLines));
        //Then
        String expectedMessage = "No apply operator was provided in the file";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testProcessWhenIncorrectOperatorProvided() {
        //Given
        List<String> listOfLines = Arrays.asList("add 2", "multiply 3", "exponentiation 2", "apply 10");

        //When
        Exception exception = assertThrows(IncorrectOperatorException.class, () -> processor.process(listOfLines));
        //Then
        String expectedMessage = "The operator at line number: 3 is unknown! Please rewrite the file";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testProcessWhenIncorrectOperandProvided() {
        //Given
        List<String> listOfLines = Arrays.asList("add 2", "multiply 3", "multiply two", "apply 10");
        //When
        Exception exception = assertThrows(IncorrectOperandException.class, () -> processor.process(listOfLines));
        //Then
        String expectedMessage = "The operand in line 3 is either missing or incorrect. Please rewrite the file!";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testProcessWhenIncorrectFormatLineProvided() {
        //Given
        List<String> listOfLines = Arrays.asList("add 2", "multiply 3", "multiply", "apply 10");
        //When
        Exception exception = assertThrows(IncorrectLineFormatException.class, () -> processor.process(listOfLines));
        //Then
        String expectedMessage = "The line from the file at number: 3 is incorrect, please rewrite the file";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
