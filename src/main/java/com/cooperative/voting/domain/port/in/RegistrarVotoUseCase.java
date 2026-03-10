package com.cooperative.voting.domain.port.in;

import com.cooperative.voting.application.command.RegistrarVotoCommand;

import java.util.UUID;

public interface RegistrarVotoUseCase {

    void execute(UUID pautaId, RegistrarVotoCommand command);

}
