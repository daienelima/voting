package com.cooperative.voting.application.service;

import com.cooperative.voting.domain.exception.SessaoNaoEncontradaException;
import com.cooperative.voting.domain.model.Sessao;
import com.cooperative.voting.domain.port.out.SessaoRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FecharSessaoService {

    private final SessaoRepositoryPort sessaoRepository;

    public FecharSessaoService(SessaoRepositoryPort sessaoRepository) {
        this.sessaoRepository = sessaoRepository;
    }

    public Sessao execute(UUID sessaoId) {

        Sessao sessao = sessaoRepository.findById(sessaoId)
                .orElseThrow(SessaoNaoEncontradaException::new);

        sessao.fechar();

        return sessaoRepository.save(sessao);
    }
}