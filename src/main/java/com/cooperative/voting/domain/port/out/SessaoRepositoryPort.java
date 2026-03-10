package com.cooperative.voting.domain.port.out;

import com.cooperative.voting.domain.model.Sessao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SessaoRepositoryPort {

    Optional<Sessao> findSessaoAtivaPorPauta(UUID pautaId);
    Sessao save(Sessao sessao);
    Optional<Sessao> findByIdAndPautaId(UUID sessaoId, UUID pautaId);
    Optional<Sessao> findById(UUID sessaoId);
    List<Sessao> buscarSessoesExpiradas();

}
