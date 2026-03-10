package com.cooperative.voting.domain.port.in;

import com.cooperative.voting.domain.model.Pauta;

import java.util.List;

public interface ListarPautasUseCase {

    List<Pauta> execute();

}