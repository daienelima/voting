package com.cooperative.voting.application.service;

import com.cooperative.voting.domain.model.ResultadoVotacao;
import com.cooperative.voting.domain.port.in.GetResultadoPautaUseCase;
import com.cooperative.voting.domain.port.out.VotoRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetResultadoPautaService implements GetResultadoPautaUseCase {

    private final VotoRepositoryPort votoRepository;

    public GetResultadoPautaService(VotoRepositoryPort votoRepository) {
        this.votoRepository = votoRepository;
    }

    @Override
    public ResultadoVotacao execute(UUID pautaId) {
        return votoRepository.contarResultado(pautaId);
    }
}