package com.cooperative.voting.infrastructure.adapter.in.rest;

import com.cooperative.voting.application.command.CriarPautaCommand;
import com.cooperative.voting.domain.port.in.*;
import com.cooperative.voting.infrastructure.adapter.in.rest.mapper.PautaResponseMapper;
import com.cooperative.voting.infrastructure.adapter.in.rest.mapper.ResultadoMapper;
import com.yourcompany.voting.api.PautasApi;
import com.yourcompany.voting.api.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class PautaController implements PautasApi {

    private final CriarPautaUseCase criarPautaUseCase;
    private final ListarPautasUseCase listarPautasUseCase;
    private final PautaResponseMapper pautaResponseMapper;
    private final BuscarPautaUseCase buscarPautaUseCase;
    private final GetResultadoPautaUseCase getResultadoPautaUseCase;
    private final ResultadoMapper resultadoMapper;

    public PautaController(CriarPautaUseCase criarPautaUseCase, ListarPautasUseCase listarPautasUseCase, PautaResponseMapper pautaResponseMapper, BuscarPautaUseCase buscarPautaUseCase, GetResultadoPautaUseCase getResultadoPautaUseCase, ResultadoMapper resultadoMapper) {
        this.criarPautaUseCase = criarPautaUseCase;
        this.listarPautasUseCase = listarPautasUseCase;
        this.pautaResponseMapper = pautaResponseMapper;
        this.buscarPautaUseCase = buscarPautaUseCase;
        this.getResultadoPautaUseCase = getResultadoPautaUseCase;
        this.resultadoMapper = resultadoMapper;
    }

    @Override
    public ResponseEntity<PautaResponse> createPauta(@Valid CriarPautaRequest criarPautaRequest) {
        var pauta = criarPautaUseCase.execute(new CriarPautaCommand(criarPautaRequest.getTitulo(),
                criarPautaRequest.getDescricao()));

        return ResponseEntity.status(HttpStatus.CREATED).body(pautaResponseMapper.toResponse(pauta));
    }

    @Override
    public ResponseEntity<PautaDetalhadaResponse> getPautaById(UUID pautaId) {
        var pauta = buscarPautaUseCase.execute(pautaId);

        return ResponseEntity.ok(
                pautaResponseMapper.toDetalhadaResponse(pauta)
        );
    }

    @Override
    public ResponseEntity<List<PautaResponse>> listPautas() {
        return ResponseEntity.ok(
                pautaResponseMapper.toResponseList(
                        listarPautasUseCase.execute()
                )
        );
    }

    @Override
    public ResponseEntity<ResultadoResponse> getResultadoByPauta(UUID pautaId) {
        var resultado = getResultadoPautaUseCase.execute(pautaId);
        return ResponseEntity.ok(
                resultadoMapper.toResponse(resultado)
        );
    }
}
