package org.math.calculator.application.shared;

public enum Operations {
    ADDITION(Operations.ADDITION_VALUE),
    SUBTRACTION(Operations.SUBTRACTION_VALUE);

    public static final String ADDITION_VALUE = "addition";
    public static final String SUBTRACTION_VALUE = "subtraction";
    private final String operation;

    Operations(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

}
