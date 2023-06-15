package org.math.calculator.application.operations;

import org.math.calculator.application.shared.Operations;
import org.springframework.stereotype.Service;

@Service(Operations.SUBTRACTION_VALUE)
public class Subtraction implements MathOperation<Double>{
    @Override
    public Double calculate(Double n1, Double n2) {
        return n1 - n2;
    }
}
