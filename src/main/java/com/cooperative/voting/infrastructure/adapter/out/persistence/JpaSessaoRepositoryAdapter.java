package com.cooperative.voting.infrastructure.adapter.out.persistence;

import com.cooperative.voting.domain.model.Sessao;
import com.cooperative.voting.domain.port.out.SessaoRepositoryPort;
import com.cooperative.voting.infrastructure.adapter.out.persistence.mapper.SessaoEntityMapper;
import com.cooperative.voting.infrastructure.adapter.out.persistence.repository.SpringDataSessaoRepository;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class JpaSessaoRepositoryAdapter implements SessaoRepositoryPort {

    private final SpringDataSessaoRepository repository;
    private final SessaoEntityMapper sessaoEntityMapper;

    public JpaSessaoRepositoryAdapter(SpringDataSessaoRepository repository, SessaoEntityMapper sessaoEntityMapper) {
        this.repository = repository;
        this.sessaoEntityMapper = sessaoEntityMapper;
    }

    @Override
    public Optional<Sessao> findSessaoAtivaPorPauta(UUID pautaId) {
        return repository
                .findByPautaId(pautaId)
                .map(sessaoEntityMapper::toDomain);
    }

    @Override
    public Sessao save(Sessao sessao) {
        return sessaoEntityMapper.toDomain(
                repository.save(
                        sessaoEntityMapper.toEntity(sessao)
                )
        );
    }

    @Override
    public Optional<Sessao> findByIdAndPautaId(UUID sessaoId, UUID pautaId) {
        return repository
                .findByIdAndPautaId(sessaoId, pautaId)
                .map(sessaoEntityMapper::toDomain);
    }

    @Override
    public Optional<Sessao> findById(UUID sessaoId) {
        return repository
                .findById(sessaoId)
                .map(sessaoEntityMapper::toDomain);
    }

    @Override
    public List<Sessao> buscarSessoesExpiradas() {
        OffsetDateTime now = OffsetDateTime.now(ZoneId.of("America/Sao_Paulo"));
        return repository.findByDataFechamentoBeforeAndEventoEncerramentoGeradoFalse(now)
                .stream()
                .map(sessaoEntityMapper::toDomain)
                .toList();
    }
}
