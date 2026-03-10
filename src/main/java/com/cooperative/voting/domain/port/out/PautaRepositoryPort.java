package com.cooperative.voting.domain.port.out;

import com.cooperative.voting.domain.model.Pauta;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PautaRepositoryPort {

    Pauta save(Pauta pauta);

    List<Pauta> findAll();

    Optional<Pauta> findById(UUID id);
}
