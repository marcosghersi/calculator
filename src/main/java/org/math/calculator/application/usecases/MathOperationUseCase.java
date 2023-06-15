package org.math.calculator.application.usecases;

import lombok.RequiredArgsConstructor;
import org.math.calculator.application.operations.MathOperation;
import org.math.calculator.application.shared.Operations;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * This class is responsible for executing the math operations.
 */
@Service
@RequiredArgsConstructor
public class MathOperationUseCase {

    /**
     * MathOperation implementations are injected by Spring.
     */
    private final Map<String, MathOperation<Double>> mathOperations;

    /**
     * Decides which operation to execute based on the operation parameter.
     *
     * @param operation
     * @param n1
     * @param n2
     * @return the operation result
     */
    public Double execute(Operations operation, Double n1, Double n2) {
        return mathOperations.get(operation.getOperation()).calculate(n1, n2);
    }
}
