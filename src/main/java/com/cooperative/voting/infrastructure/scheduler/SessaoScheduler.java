package com.cooperative.voting.infrastructure.scheduler;

import com.cooperative.voting.application.service.EncerrarSessaoService;
import com.cooperative.voting.domain.port.out.SessaoRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SessaoScheduler {

    private final EncerrarSessaoService encerrarSessaoService;
    private final SessaoRepositoryPort sessaoRepository;

    public SessaoScheduler(
            EncerrarSessaoService encerrarSessaoService,
            SessaoRepositoryPort sessaoRepository
    ) {
        this.encerrarSessaoService = encerrarSessaoService;
        this.sessaoRepository = sessaoRepository;
    }

    @Scheduled(fixedDelay = 60000)
    public void encerrarSessoesExpiradas() {
        log.info("Executando scheduler de encerramento de sessões expiradas");

        var sessoes = sessaoRepository.buscarSessoesExpiradas();
        log.info("Encontradas {} sessões expiradas", sessoes.size());

        for (var sessao : sessoes) {
            log.info("Encerrando sessão: {}", sessao.getId());
            encerrarSessaoService.execute(sessao.getId());
        }
    }
}