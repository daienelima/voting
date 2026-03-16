package com.cooperative.voting.infrastructure.adapter.out.persistence;

import com.cooperative.voting.domain.model.Sessao;
import com.cooperative.voting.domain.model.enums.StatusSessao;
import com.cooperative.voting.infrastructure.adapter.out.persistence.mapper.SessaoEntityMapper;
import com.cooperative.voting.infrastructure.adapter.out.persistence.repository.SpringDataSessaoRepository;
import com.cooperative.voting.infrastructure.config.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class JpaSessaoRepositoryAdapterIT extends AbstractIntegrationTest {

    @Autowired
    private SpringDataSessaoRepository repository;

    @Autowired
    private SessaoEntityMapper mapper;

    private JpaSessaoRepositoryAdapter adapter;

    @BeforeEach
    void setup() {
        adapter = new JpaSessaoRepositoryAdapter(repository, mapper);
    }

    @Test
    void deveSalvarSessao() {

        UUID sessaoId = UUID.randomUUID();
        UUID pautaId = UUID.randomUUID();

        Sessao sessao = new Sessao(
                sessaoId,
                pautaId,
                OffsetDateTime.now(),
                OffsetDateTime.now().plusMinutes(5),
                StatusSessao.ABERTA,
                false
        );

        Sessao saved = adapter.save(sessao);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isEqualTo(sessaoId);
        assertThat(saved.getPautaId()).isEqualTo(pautaId);
    }

    @Test
    void deveBuscarSessaoPorId() {

        UUID sessaoId = UUID.randomUUID();
        UUID pautaId = UUID.randomUUID();

        Sessao sessao = new Sessao(
                sessaoId,
                pautaId,
                OffsetDateTime.now(),
                OffsetDateTime.now().plusMinutes(10),
                StatusSessao.ABERTA,
                false
        );

        adapter.save(sessao);

        var result = adapter.findById(sessaoId);

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(sessaoId);
    }

    @Test
    void deveBuscarSessaoAtivaPorPauta() {

        UUID sessaoId = UUID.randomUUID();
        UUID pautaId = UUID.randomUUID();

        Sessao sessao = new Sessao(
                sessaoId,
                pautaId,
                OffsetDateTime.now(),
                OffsetDateTime.now().plusMinutes(10),
                StatusSessao.ABERTA,
                false
        );

        adapter.save(sessao);

        var result = adapter.findSessaoAtivaPorPauta(pautaId);

        assertThat(result).isPresent();
        assertThat(result.get().getPautaId()).isEqualTo(pautaId);
    }

    @Test
    void deveBuscarSessoesExpiradas() {

        UUID sessaoId = UUID.randomUUID();
        UUID pautaId = UUID.randomUUID();

        Sessao sessao = new Sessao(
                sessaoId,
                pautaId,
                OffsetDateTime.now().minusMinutes(10),
                OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")).minusMinutes(1),
                StatusSessao.ABERTA,
                false
        );

        adapter.save(sessao);

        var expiradas = adapter.buscarSessoesExpiradas();

        assertThat(expiradas).isNotEmpty();
    }
}