package org.math.calculator.application.usecases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.math.calculator.application.operations.MathOperation;
import org.math.calculator.application.shared.Operations;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Objects;

/**
 * This class is responsible for executing the math operations.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MathOperationUseCase implements OperationUseCase<BigDecimal> {

    /**
     * MathOperation implementations are injected by Spring.
     */
    private final Map<String, MathOperation<BigDecimal>> mathOperations;

    /**
     * Decides which operation to execute based on the operation parameter.
     *
     * @param operation, operation to execute
     * @param n1,        first number
     * @param n2         second number
     * @param precision, precision to round the result, null if no precision is required
     * @return the operation result
     */
    public BigDecimal execute(Operations operation, BigDecimal n1, BigDecimal n2, Integer precision, RoundingMode roundingMode) {

        if (!Objects.isNull(precision) && precision < 0) {
            throw new IllegalArgumentException("Precision must be greater than 0");
        }

        BigDecimal result = mathOperations.get(operation.getOperation()).calculate(n1, n2);
        log.info("execute operation: {}, n1: {}, n2: {}, result: {}", operation, n1, n2, result);

        RoundingMode roundingModeToUse = Objects.isNull(roundingMode) ? RoundingMode.HALF_UP : roundingMode;

        return Objects.isNull(precision) ? result : result.setScale(precision, roundingModeToUse);
    }
}
