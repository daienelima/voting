package com.cooperative.voting.infrastructure.adapter.out.persistence;

import com.cooperative.voting.domain.model.ResultadoVotacao;
import com.cooperative.voting.domain.model.Voto;
import com.cooperative.voting.domain.port.out.VotoRepositoryPort;
import com.cooperative.voting.infrastructure.adapter.out.persistence.mapper.VotoEntityMapper;
import com.cooperative.voting.infrastructure.adapter.out.persistence.projection.ResultadoVotacaoProjection;
import com.cooperative.voting.infrastructure.adapter.out.persistence.repository.SpringDataVotoRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JpaVotoRepositoryAdapter implements VotoRepositoryPort {

    private final SpringDataVotoRepository repository;
    private final VotoEntityMapper mapper;

    public JpaVotoRepositoryAdapter(SpringDataVotoRepository repository, VotoEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Voto save(Voto voto) {
        return mapper.toDomain(
            repository.save(
                    mapper.toEntity(voto)
            ));
    }

    @Override
    public ResultadoVotacao contarResultado(UUID pautaId) {
        ResultadoVotacaoProjection result = repository.contarResultado(pautaId);

        long totalSim = result.totalSim() == null ? 0 : result.totalSim();
        long totalNao = result.totalNao() == null ? 0 : result.totalNao();

        return new ResultadoVotacao(
                pautaId,
                totalSim,
                totalNao
        );
    }

    @Override
    public boolean existsBySessaoIdAndAssociadoId(UUID sessaoId, String associadoId) {
        return repository.existsBySessaoIdAndAssociadoId(sessaoId, associadoId);
    }
}
