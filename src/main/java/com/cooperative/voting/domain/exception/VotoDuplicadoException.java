package com.cooperative.voting.domain.exception;

public class VotoDuplicadoException extends RuntimeException {

    public VotoDuplicadoException() {
        super("O associado já realizou voto nesta sessão.");
    }

    public VotoDuplicadoException(String message) {
        super(message);
    }
}
