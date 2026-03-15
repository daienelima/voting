package com.cooperative.voting.application.service;

import com.cooperative.voting.domain.exception.PautaNaoEncontradaException;
import com.cooperative.voting.domain.model.Sessao;
import com.cooperative.voting.domain.port.in.BuscarSessaoUseCase;
import com.cooperative.voting.domain.port.out.SessaoRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BuscarSessaoService implements BuscarSessaoUseCase {

    private final SessaoRepositoryPort sessaoRepository;

    public BuscarSessaoService(SessaoRepositoryPort sessaoRepository) {
        this.sessaoRepository = sessaoRepository;
    }

    @Override
    public Sessao execute(UUID pautaId, UUID sessaoId) {

        return sessaoRepository
                .findByIdAndPautaId(sessaoId, pautaId)
                .orElseThrow(() ->
                        new PautaNaoEncontradaException("Sessão não encontrada"));
    }

}