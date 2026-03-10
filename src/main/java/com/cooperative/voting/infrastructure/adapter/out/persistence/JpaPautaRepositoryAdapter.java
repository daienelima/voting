package com.cooperative.voting.infrastructure.adapter.out.persistence;

import com.cooperative.voting.domain.model.Pauta;
import com.cooperative.voting.domain.port.out.PautaRepositoryPort;
import com.cooperative.voting.infrastructure.adapter.out.persistence.mapper.PautaEntityMapper;
import com.cooperative.voting.infrastructure.adapter.out.persistence.repository.SpringDataPautaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class JpaPautaRepositoryAdapter implements PautaRepositoryPort {

    private final SpringDataPautaRepository repository;
    private final PautaEntityMapper mapper;

    public JpaPautaRepositoryAdapter(SpringDataPautaRepository repository, PautaEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Pauta save(Pauta pauta) {

        var pautaEntity = repository.save(mapper.toEntity(pauta));
        return mapper.toDomain(pautaEntity);
    }

    @Override
    public List<Pauta> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Pauta> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }
}