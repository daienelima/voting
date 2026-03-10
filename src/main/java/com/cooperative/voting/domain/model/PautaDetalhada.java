package com.cooperative.voting.domain.model;

public record PautaDetalhada(
        Pauta pauta,
        ResultadoVotacao resultado
) {}