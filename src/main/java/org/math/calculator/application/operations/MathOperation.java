package org.math.calculator.application.operations;

public interface MathOperation <T extends Number> {
    T calculate(T n1, T n2);

}
