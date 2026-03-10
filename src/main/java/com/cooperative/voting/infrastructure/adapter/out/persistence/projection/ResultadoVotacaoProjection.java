package com.cooperative.voting.infrastructure.adapter.out.persistence.projection;

public record ResultadoVotacaoProjection(
        Long totalSim,
        Long totalNao
) {
}
