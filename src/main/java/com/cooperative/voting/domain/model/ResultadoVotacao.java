package com.cooperative.voting.domain.model;

import com.cooperative.voting.domain.model.enums.ResultadoFinal;

import java.util.UUID;

public record ResultadoVotacao(
        UUID pautaId,
        long totalSim,
        long totalNao
) {

    public ResultadoFinal getResultadoFinal() {
        if (totalSim > totalNao) {
            return ResultadoFinal.APROVADO;
        }

        if (totalNao > totalSim) {
            return ResultadoFinal.REPROVADO;
        }

        return ResultadoFinal.EMPATE;
    }
}