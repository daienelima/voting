package com.cooperative.voting.domain.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public record SessaoVotacao(
        UUID id,
        UUID pautaId,
        OffsetDateTime dataAbertura,
        OffsetDateTime dataFechamento
) {
    public boolean estaEncerrada() {
        return OffsetDateTime.now().isAfter(dataFechamento);
    }
}