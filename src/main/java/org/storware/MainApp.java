package org.storware;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.storware.calculator.Calculator;


public class MainApp {
    private static Logger log = LogManager.getLogger(MainApp.class);

    public static void main(String... args) {
        Calculator calculator = new Calculator();
        try {
            String path = args[0];
            calculator.calculate(path);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("No filepath was provided");
        }

    }
}
