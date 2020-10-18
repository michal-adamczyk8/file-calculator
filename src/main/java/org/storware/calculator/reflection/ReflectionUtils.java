package org.storware.calculator.reflection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import org.storware.calculator.annotation.Operator;
import org.storware.calculator.contant.Constants;
import org.storware.calculator.operations.Operation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class ReflectionUtils {

    private static Logger log = LogManager.getLogger(ReflectionUtils.class);

    private final Map<String, Operation> operatorNames;

    public ReflectionUtils() {
        this.operatorNames = getAllOperatorsNames();
    }

    public Map<String, Operation> getOperatorNames() {
        return operatorNames;
    }

    /**
     * Method which uses reflection, responsible for getting all classes with Operator annotation. It returns map of operator name
     * and corresponding operator object that can be found in a defined package.
     *
     * @return
     */
    private Map<String, Operation> getAllOperatorsNames() {
        Map<String, Operation> operatorsNames = new HashMap<>();
        Reflections reflections = new Reflections(Constants.PACKAGE_NAME);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Operator.class);
        for (Class<?> clazz : classes) {
            try {
                Operation operation = (Operation) Class.forName(clazz.getName()).getConstructor().newInstance();
                String operatorName = operation.getOperatorName();
                operatorsNames.put(operatorName, operation);
            } catch (ReflectiveOperationException e) {
                log.error("Error occurred while trying to use reflection!", e);
            }
        }
        return operatorsNames;
    }
}
