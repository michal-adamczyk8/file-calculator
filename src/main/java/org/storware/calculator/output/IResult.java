package org.storware.calculator.output;

public interface IResult<R, O> {
    String output();

    R getResult();

    O reportOutput();
}
