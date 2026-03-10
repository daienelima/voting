package com.cooperative.voting.infrastructure.adapter.in.rest.mapper;

import com.cooperative.voting.domain.model.ResultadoVotacao;
import com.yourcompany.voting.api.model.ResultadoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResultadoMapper {

    default ResultadoResponse toResponse(ResultadoVotacao resultado) {

        return new ResultadoResponse()
                .totalSim((int) resultado.totalSim())
                .totalNao((int) resultado.totalNao())
                .resultado(ResultadoResponse.ResultadoEnum
                        .valueOf(resultado.getResultadoFinal().name()));
    }
}