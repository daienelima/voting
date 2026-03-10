package com.cooperative.voting.infrastructure.adapter.in.rest;

import com.cooperative.voting.domain.model.Sessao;
import com.cooperative.voting.domain.model.SessaoVotacao;
import com.cooperative.voting.domain.port.in.BuscarSessaoUseCase;
import com.cooperative.voting.domain.port.in.CriarSessaoUseCase;
import com.cooperative.voting.infrastructure.adapter.in.rest.mapper.SessaoMapper;
import com.yourcompany.voting.api.SessesApi;
import com.yourcompany.voting.api.model.AbrirSessaoRequest;
import com.yourcompany.voting.api.model.SessaoResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class SessaoController implements SessesApi {

    private final CriarSessaoUseCase criarSessaoUseCase;
    private final BuscarSessaoUseCase buscarSessaoUseCase;
    private final SessaoMapper sessaoMapper;

    public SessaoController(CriarSessaoUseCase criarSessaoUseCase, BuscarSessaoUseCase buscarSessaoUseCase, SessaoMapper sessaoMapper) {
        this.criarSessaoUseCase = criarSessaoUseCase;
        this.buscarSessaoUseCase = buscarSessaoUseCase;
        this.sessaoMapper = sessaoMapper;
    }

    @Override
    public ResponseEntity<SessaoResponse> createSessao(UUID pautaId, @Valid AbrirSessaoRequest abrirSessaoRequest) {

        Sessao sessao = criarSessaoUseCase.execute(
                pautaId,
                abrirSessaoRequest != null ? abrirSessaoRequest.getDuracaoMinutos() : null
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sessaoMapper.toResponse(sessao));
    }

    @Override
    public ResponseEntity<SessaoResponse> getSessaoById(UUID pautaId, UUID sessaoId) {
        Sessao sessao =
                buscarSessaoUseCase.execute(pautaId, sessaoId);

        return ResponseEntity.ok(
                sessaoMapper.toResponse(sessao)
        );
    }
}
