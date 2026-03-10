package com.cooperative.voting.infrastructure.adapter.in.rest.mapper;

import com.cooperative.voting.domain.model.Pauta;
import com.cooperative.voting.domain.model.PautaDetalhada;
import com.cooperative.voting.domain.model.ResultadoVotacao;
import com.yourcompany.voting.api.model.PautaDetalhadaResponse;
import com.yourcompany.voting.api.model.PautaResponse;
import com.yourcompany.voting.api.model.ResultadoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PautaResponseMapper {

    @Autowired
    protected ResultadoMapper resultadoMapper;

    public abstract PautaResponse toResponse(Pauta domain);

    public abstract List<PautaResponse> toResponseList(List<Pauta> domainList);

    @Mapping(target = "id", source = "pauta.id")
    @Mapping(target = "titulo", source = "pauta.titulo")
    @Mapping(target = "descricao", source = "pauta.descricao")
    @Mapping(target = "dataCriacao", source = "pauta.dataCriacao")
    @Mapping(target = "resultado", source = "resultado")
    public abstract PautaDetalhadaResponse toDetalhadaResponse(PautaDetalhada domain);

    protected ResultadoResponse map(ResultadoVotacao resultado) {
        return resultadoMapper.toResponse(resultado);
    }
}
