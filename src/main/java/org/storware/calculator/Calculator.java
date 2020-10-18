package org.storware.calculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.storware.calculator.exceptions.IncorrectLineFormatException;
import org.storware.calculator.exceptions.IncorrectOperandException;
import org.storware.calculator.exceptions.IncorrectOperatorException;
import org.storware.calculator.exceptions.NoApplyOperatorException;
import org.storware.calculator.exceptions.NoLinesProvidedException;
import org.storware.calculator.file.FileReader;
import org.storware.calculator.output.Result;
import org.storware.calculator.processor.Processor;
import org.storware.calculator.reflection.ReflectionUtils;

import java.util.List;

public class Calculator {

    private static Logger log = LogManager.getLogger(Calculator.class);
    public Processor processor;

    public Calculator() {
        processor = new Processor(new ReflectionUtils());
    }

    public void calculate(String filePath) {
        try {
            List<String> lines = FileReader.readFile(filePath);
            Result result = processor.process(lines);
            result.reportOutput();
        } catch (IncorrectOperandException | IncorrectOperatorException
                | NoApplyOperatorException | NoLinesProvidedException | IncorrectLineFormatException e) {
            log.error(e.getMessage(), e);
        }
    }
}
