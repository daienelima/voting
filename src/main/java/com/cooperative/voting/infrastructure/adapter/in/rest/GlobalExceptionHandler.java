package com.cooperative.voting.infrastructure.adapter.in.rest;

import com.cooperative.voting.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SessaoEncerradaException.class)
    public ResponseEntity<ErrorResponse> handleSessaoEncerrada(
            SessaoEncerradaException ex) {

        ErrorResponse error = new ErrorResponse(
                OffsetDateTime.now(),
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(PautaNaoEncontradaException.class)
    public ResponseEntity<ErrorResponse> handlePautaNaoEncontrada(
            PautaNaoEncontradaException ex
    ) {

        ErrorResponse error = new ErrorResponse(
                OffsetDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(VotoDuplicadoException.class)
    public ResponseEntity<ErrorResponse> handleVotoDuplicado(
            VotoDuplicadoException ex) {

        ErrorResponse error = new ErrorResponse(
                OffsetDateTime.now(),
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(SessaoJaExisteException.class)
    public ResponseEntity<ErrorResponse> handleSessaoJaExiste(
            SessaoJaExisteException ex) {

        ErrorResponse error = new ErrorResponse(
                OffsetDateTime.now(),
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(CpfInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleCpfInvalido(
            CpfInvalidoException ex) {

        ErrorResponse error = new ErrorResponse(
                OffsetDateTime.now(),
                422,
                ex.getMessage()
        );

        return ResponseEntity
                .status(422)
                .body(error);
    }

    @ExceptionHandler(CpfValidationException.class)
    public ResponseEntity<ErrorResponse> handleCpfValidation(
            CpfValidationException ex) {

        ErrorResponse error = new ErrorResponse(
                OffsetDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
