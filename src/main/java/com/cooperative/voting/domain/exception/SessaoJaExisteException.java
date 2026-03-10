package com.cooperative.voting.domain.exception;

public class SessaoJaExisteException extends RuntimeException {

    public SessaoJaExisteException() {
        super("Sessão já existe para essa pauta");
    }
}
