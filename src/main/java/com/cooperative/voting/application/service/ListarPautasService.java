package com.cooperative.voting.application.service;

import com.cooperative.voting.domain.model.Pauta;
import com.cooperative.voting.domain.port.in.ListarPautasUseCase;
import com.cooperative.voting.domain.port.out.PautaRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarPautasService implements ListarPautasUseCase {

    private final PautaRepositoryPort pautaRepositoryPort;

    public ListarPautasService(PautaRepositoryPort pautaRepositoryPort) {
        this.pautaRepositoryPort = pautaRepositoryPort;
    }

    @Override
    public List<Pauta> execute() {
        return pautaRepositoryPort.findAll();
    }
}