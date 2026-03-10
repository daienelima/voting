package com.cooperative.voting.domain.port.in;

import com.cooperative.voting.domain.model.PautaDetalhada;

import java.util.UUID;

public interface BuscarPautaUseCase {

    PautaDetalhada execute(UUID pautaId);

}