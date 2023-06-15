package org.math.calculator.infraestructure.rest.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@Builder
public class OperationResult {
    private BigDecimal result;
}
