package com.cooperative.voting.infrastructure.adapter.out.persistence.repository;

import com.cooperative.voting.infrastructure.adapter.out.persistence.entity.SessaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataSessaoRepository
        extends JpaRepository<SessaoEntity, UUID> {

    Optional<SessaoEntity> findByPautaIdAndDataFechamentoAfter(
            UUID pautaId,
            OffsetDateTime now
    );

    Optional<SessaoEntity> findByIdAndPautaId(UUID id, UUID pautaId);

    List<SessaoEntity> findByDataFechamentoBeforeAndEventoEncerramentoGeradoFalse(OffsetDateTime now);
}
