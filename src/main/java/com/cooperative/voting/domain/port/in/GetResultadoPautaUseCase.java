package com.cooperative.voting.domain.port.in;

import com.cooperative.voting.domain.model.ResultadoVotacao;

import java.util.UUID;

public interface GetResultadoPautaUseCase {

    ResultadoVotacao execute(UUID pautaId);

}