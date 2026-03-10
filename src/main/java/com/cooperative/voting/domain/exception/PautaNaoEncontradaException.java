package com.cooperative.voting.domain.exception;

import java.util.UUID;

public class PautaNaoEncontradaException extends RuntimeException {

    public PautaNaoEncontradaException(UUID pautaId) {
        super("Pauta não encontrada para o id: " + pautaId);
    }

    public PautaNaoEncontradaException(String message) {
        super(message);
    }
}