package org.math.calculator.infraestructure.rest.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.math.calculator.application.shared.Operations;
import org.math.calculator.application.usecases.MathOperationUseCase;
import org.math.calculator.infraestructure.rest.response.OperationResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
@RequiredArgsConstructor
@Slf4j
public class CalculatorController {

    private final MathOperationUseCase mathOperationUseCase;

    @GetMapping("/{operation}/{n1}/{n2}")
    public ResponseEntity<OperationResult> calculate(@PathVariable Operations operation, @PathVariable Double n1, @PathVariable Double n2) {
        log.info("calculate operation: {}, n1: {}, n2: {}", operation, n1, n2);
        return ResponseEntity.ok(OperationResult.builder().result(mathOperationUseCase.execute(operation, n1, n2)).build());
    }
}
