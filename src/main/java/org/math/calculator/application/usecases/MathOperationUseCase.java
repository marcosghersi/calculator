package org.math.calculator.application.usecases;

import org.math.calculator.application.operations.MathOperation;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.math.calculator.application.shared.Operations;

@RequiredArgsConstructor
public class MathOperationUseCase {

    private final Map<String, MathOperation<Double>> mathOperations;

    public Double execute(Operations operation, Double n1, Double n2) {
        return mathOperations.get(operation.getOperation()).calculate(n1, n2);
    }
}
