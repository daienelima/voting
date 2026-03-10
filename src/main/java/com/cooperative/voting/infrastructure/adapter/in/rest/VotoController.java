package com.cooperative.voting.infrastructure.adapter.in.rest;

import com.cooperative.voting.application.command.RegistrarVotoCommand;
import com.cooperative.voting.domain.model.enums.TipoVoto;
import com.cooperative.voting.domain.port.in.RegistrarVotoUseCase;
import com.yourcompany.voting.api.VotosApi;
import com.yourcompany.voting.api.model.RegistrarVotoRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class VotoController implements VotosApi {

    private final RegistrarVotoUseCase registrarVotoUseCase;

    public VotoController(RegistrarVotoUseCase registrarVotoUseCase) {
        this.registrarVotoUseCase = registrarVotoUseCase;
    }

    @Override
    public ResponseEntity<Void> registerVoto(UUID pautaId, @Valid RegistrarVotoRequest registrarVotoRequest) {
        registrarVotoUseCase.execute(
                pautaId,
                new RegistrarVotoCommand(
                        registrarVotoRequest.getAssociadoId(),
                        registrarVotoRequest.getCpf(),
                        TipoVoto.valueOf(registrarVotoRequest.getVoto().name())
                )
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
