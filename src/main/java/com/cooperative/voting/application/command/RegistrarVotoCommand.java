package com.cooperative.voting.application.command;

import com.cooperative.voting.domain.model.enums.TipoVoto;

public record RegistrarVotoCommand(
        String associadoId,
        String cpf,
        TipoVoto voto
) {}
