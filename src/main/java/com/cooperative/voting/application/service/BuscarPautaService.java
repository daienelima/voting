package com.cooperative.voting.application.service;

import com.cooperative.voting.domain.exception.PautaNaoEncontradaException;
import com.cooperative.voting.domain.model.Pauta;
import com.cooperative.voting.domain.model.PautaDetalhada;
import com.cooperative.voting.domain.model.ResultadoVotacao;
import com.cooperative.voting.domain.port.in.BuscarPautaUseCase;
import com.cooperative.voting.domain.port.out.PautaRepositoryPort;
import com.cooperative.voting.domain.port.out.VotoRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BuscarPautaService implements BuscarPautaUseCase {

    private final PautaRepositoryPort pautaRepository;
    private final VotoRepositoryPort votoRepository;

    public BuscarPautaService(PautaRepositoryPort pautaRepository, VotoRepositoryPort votoRepository) {
        this.pautaRepository = pautaRepository;
        this.votoRepository = votoRepository;
    }

    @Override
    public PautaDetalhada execute(UUID pautaId) {

        Pauta pauta = pautaRepository
                .findById(pautaId)
                .orElseThrow(() -> new PautaNaoEncontradaException(pautaId));

        ResultadoVotacao resultado =
                votoRepository.contarResultado(pautaId);

        return new PautaDetalhada(
                pauta,
                resultado
        );
    }
}