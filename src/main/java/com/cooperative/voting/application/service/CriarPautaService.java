package com.cooperative.voting.application.service;

import com.cooperative.voting.application.command.CriarPautaCommand;
import com.cooperative.voting.domain.model.Pauta;
import com.cooperative.voting.domain.port.in.CriarPautaUseCase;
import com.cooperative.voting.domain.port.out.PautaRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CriarPautaService implements CriarPautaUseCase {

    private final PautaRepositoryPort repository;

    public CriarPautaService(PautaRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Pauta execute(CriarPautaCommand command) {

        Pauta nova = Pauta.nova(
                command.titulo(),
                command.descricao()
        );

        return repository.save(nova);
    }
}
