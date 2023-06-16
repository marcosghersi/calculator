package org.math.calculator.application.usecases;

import org.math.calculator.application.shared.Operations;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface OperationUseCase<T> {

    T execute(Operations operation, BigDecimal n1, BigDecimal n2, Integer precision, RoundingMode roundingMode);
}
