package com.cooperative.voting.domain.exception;

public class CpfInvalidoException extends RuntimeException {

    public CpfInvalidoException() {
        super("CPF inválido ou não autorizado para votar.");
    }
}
