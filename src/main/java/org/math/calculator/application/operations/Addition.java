package org.math.calculator.application.operations;

import org.math.calculator.application.shared.Operations;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service(Operations.ADDITION_VALUE)
public class Addition implements MathOperation<BigDecimal> {
    @Override
    public BigDecimal calculate(BigDecimal n1, BigDecimal n2) {
        return n1.add(n2);
    }
}
