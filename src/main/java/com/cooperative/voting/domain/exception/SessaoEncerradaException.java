package com.cooperative.voting.domain.exception;

public class SessaoEncerradaException extends RuntimeException {

    public SessaoEncerradaException() {
        super("A sessão de votação está encerrada.");
    }

    public SessaoEncerradaException(String message) {
        super(message);
    }
}
