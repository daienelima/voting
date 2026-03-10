package com.cooperative.voting.domain.port.out;

import com.cooperative.voting.domain.model.ResultadoVotacao;
import com.cooperative.voting.domain.model.Voto;

import java.util.UUID;

public interface VotoRepositoryPort {

    Voto save(Voto voto);
    ResultadoVotacao contarResultado(UUID pautaId);
    boolean existsBySessaoIdAndAssociadoId(UUID sessaoId, String associadoId);
}
