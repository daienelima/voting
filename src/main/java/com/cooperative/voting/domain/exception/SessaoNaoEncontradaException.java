package com.cooperative.voting.domain.exception;

public class SessaoNaoEncontradaException extends RuntimeException {

    public SessaoNaoEncontradaException() {
        super("Sessão de votação não encontrada.");
    }
}