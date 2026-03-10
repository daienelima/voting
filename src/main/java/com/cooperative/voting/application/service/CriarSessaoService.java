package com.cooperative.voting.application.service;

import com.cooperative.voting.domain.exception.SessaoJaExisteException;
import com.cooperative.voting.domain.model.Sessao;
import com.cooperative.voting.domain.port.in.CriarSessaoUseCase;
import com.cooperative.voting.domain.port.out.PautaRepositoryPort;
import com.cooperative.voting.domain.port.out.SessaoRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CriarSessaoService implements CriarSessaoUseCase {

    private final SessaoRepositoryPort sessaoRepository;
    private final PautaRepositoryPort pautaRepository;

    public CriarSessaoService(SessaoRepositoryPort sessaoRepository, PautaRepositoryPort pautaRepository) {
        this.sessaoRepository = sessaoRepository;
        this.pautaRepository = pautaRepository;
    }

    @Override
    public Sessao execute(UUID pautaId, Integer duracaoMinutos) {

        pautaRepository.findById(pautaId).orElseThrow();

        sessaoRepository.findSessaoAtivaPorPauta(pautaId)
                .ifPresent(s -> {
                    throw new SessaoJaExisteException();
                });

        Sessao sessao = Sessao.abrir(pautaId, duracaoMinutos);

        return sessaoRepository.save(sessao);
    }

}