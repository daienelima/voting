package com.cooperative.voting.infrastructure.adapter.out.persistence.repository;

import com.cooperative.voting.infrastructure.adapter.out.persistence.entity.VotoEntity;
import com.cooperative.voting.infrastructure.adapter.out.persistence.projection.ResultadoVotacaoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface SpringDataVotoRepository extends JpaRepository<VotoEntity, UUID> {

    boolean existsBySessaoIdAndAssociadoId(UUID sessaoId, String associadoId);

    @Query("""
        SELECT new com.cooperative.voting.infrastructure.adapter.out.persistence.projection.ResultadoVotacaoProjection(
            SUM(CASE WHEN v.escolha = 'SIM' THEN 1 ELSE 0 END),
            SUM(CASE WHEN v.escolha = 'NAO' THEN 1 ELSE 0 END)
        )
        FROM VotoEntity v
        JOIN SessaoEntity s ON v.sessaoId = s.id
        WHERE s.pautaId = :pautaId
    """)

    ResultadoVotacaoProjection contarResultado(UUID pautaId);
}
