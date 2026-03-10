package com.cooperative.voting.application.event;

import java.util.UUID;

public record SessaoEncerradaEvent(

        UUID sessaoId,
        UUID pautaId,
        long totalSim,
        long totalNao,
        String resultado

) {}