package org.math.calculator.infraestructure.rest.controllers;

import io.corp.calculator.TracerImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.math.calculator.application.shared.Operations;
import org.math.calculator.application.usecases.MathOperationUseCase;
import org.math.calculator.infraestructure.rest.response.OperationResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping("/calculator")
@RequiredArgsConstructor
@Slf4j
public class CalculatorController {

    private final MathOperationUseCase mathOperationUseCase;

    private final TracerImpl tracer;

    @GetMapping("/{operation}/{n1}/{n2}")
    public ResponseEntity<OperationResult> calculate(@PathVariable Operations operation, @PathVariable BigDecimal n1,
                                                     @PathVariable BigDecimal n2, @RequestParam(required = false) Integer precision,
                                                     @RequestParam(required = false) RoundingMode roundingMode) {
        log.info("calculate operation: {}, n1: {}, n2: {}", operation, n1, n2);

        BigDecimal result = mathOperationUseCase.execute(operation, n1, n2, precision, roundingMode);
        log.info("calculate result: {}", result);
        tracer.trace(result);

        return ResponseEntity.ok(OperationResult.builder().result(result).build());
    }
}
