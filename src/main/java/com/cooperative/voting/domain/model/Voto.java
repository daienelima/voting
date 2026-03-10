package com.cooperative.voting.domain.model;

import com.cooperative.voting.domain.model.enums.TipoVoto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record Voto(
        UUID id,
        UUID sessaoId,
        String associadoId,
        TipoVoto escolha,
        OffsetDateTime dataHora
) {

    public static Voto novo(UUID sessaoId,
                            String associadoId,
                            TipoVoto escolha) {

        return new Voto(
                UUID.randomUUID(),
                sessaoId,
                associadoId,
                escolha,
                OffsetDateTime.now()
        );
    }
}
