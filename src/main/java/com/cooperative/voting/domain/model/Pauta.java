package com.cooperative.voting.domain.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public record Pauta(
        UUID id,
        String titulo,
        String descricao,
        OffsetDateTime dataCriacao
) {

    public static Pauta nova(String titulo, String descricao) {
        return new Pauta(
                UUID.randomUUID(),
                titulo,
                descricao,
                OffsetDateTime.now()
        );
    }
}
