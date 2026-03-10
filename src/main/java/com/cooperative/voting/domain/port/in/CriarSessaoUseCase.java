package com.cooperative.voting.domain.port.in;

import com.cooperative.voting.domain.model.Sessao;
import java.util.UUID;

public interface CriarSessaoUseCase {

    Sessao execute(UUID pautaId, Integer duracaoMinutos);

}