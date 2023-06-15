package org.math.calculator.infraestructure.rest.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class OperationResult {
    private Double result;
}
