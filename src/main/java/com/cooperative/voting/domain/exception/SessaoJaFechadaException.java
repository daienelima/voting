package com.cooperative.voting.domain.exception;

public class SessaoJaFechadaException extends RuntimeException {

    public SessaoJaFechadaException() {
        super("A sessão de votação já está fechada.");
    }
}