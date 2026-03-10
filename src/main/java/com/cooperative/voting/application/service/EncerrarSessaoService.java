package com.cooperative.voting.application.service;

import com.cooperative.voting.application.event.SessaoEncerradaEvent;
import com.cooperative.voting.domain.exception.SessaoNaoEncontradaException;
import com.cooperative.voting.domain.model.ResultadoVotacao;
import com.cooperative.voting.domain.port.out.OutboxEventPort;
import com.cooperative.voting.domain.port.out.SessaoRepositoryPort;
import com.cooperative.voting.domain.port.out.VotoRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EncerrarSessaoService {

    private final SessaoRepositoryPort sessaoRepository;
    private final VotoRepositoryPort votoRepository;
    private final OutboxEventPort outboxEventPort;


    public EncerrarSessaoService(SessaoRepositoryPort sessaoRepository, VotoRepositoryPort votoRepository, OutboxEventPort outboxEventPort) {
        this.sessaoRepository = sessaoRepository;
        this.votoRepository = votoRepository;
        this.outboxEventPort = outboxEventPort;
    }

    public void execute(UUID sessaoId) {
        var sessao = sessaoRepository.findById(sessaoId)
                .orElseThrow(SessaoNaoEncontradaException::new);

        ResultadoVotacao resultadoVotacao = votoRepository.contarResultado(sessaoId);

        SessaoEncerradaEvent event = new SessaoEncerradaEvent(
                sessaoId,
                sessao.getPautaId(),
                resultadoVotacao.totalSim(),
                resultadoVotacao.totalNao(),
                resultadoVotacao.getResultadoFinal().name()
        );

        outboxEventPort.salvarEvento(event);
    }
}
