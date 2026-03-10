package com.cooperative.voting.domain.port.in;

import com.cooperative.voting.application.command.CriarPautaCommand;
import com.cooperative.voting.domain.model.Pauta;

public interface CriarPautaUseCase {

    Pauta execute(CriarPautaCommand command);
}
