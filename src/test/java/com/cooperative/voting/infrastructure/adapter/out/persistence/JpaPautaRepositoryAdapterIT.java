package com.cooperative.voting.infrastructure.adapter.out.persistence;

import com.cooperative.voting.domain.model.Pauta;
import com.cooperative.voting.infrastructure.config.AbstractIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class JpaPautaRepositoryAdapterIT extends AbstractIntegrationTest {

    @Autowired
    private JpaPautaRepositoryAdapter repository;

    private Pauta pauta;

    @BeforeEach
    void setup() {
        pauta = Pauta.nova("Nova pauta", "Descrição da nova pauta");
    }

    @Test
    void shouldSavePauta() {

        Pauta saved = repository.save(pauta);

        assertThat(saved).isNotNull();
        assertThat(saved.id()).isEqualTo(pauta.id());
    }

    @Test
    void shouldFindById() {

        repository.save(pauta);

        Optional<Pauta> result = repository.findById(pauta.id());

        assertThat(result).isPresent();
        assertThat(result.get().titulo()).isEqualTo("Nova pauta");
    }

    @Test
    void shouldFindAll() {

        repository.save(pauta);

        List<Pauta> pautas = repository.findAll();

        assertThat(pautas).isNotEmpty();
    }
}