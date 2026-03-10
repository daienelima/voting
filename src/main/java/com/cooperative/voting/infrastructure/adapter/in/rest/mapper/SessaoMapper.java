package com.cooperative.voting.infrastructure.adapter.in.rest.mapper;

import com.cooperative.voting.domain.model.Sessao;
import com.yourcompany.voting.api.model.SessaoResponse;
import com.yourcompany.voting.api.model.StatusSessao;
import org.mapstruct.*;

@Mapper(componentModel = "spring", imports = {StatusSessao.class})
public interface SessaoMapper {

    @ValueMapping(source = "ABERTA", target = "OPEN")
    @ValueMapping(source = "ENCERRADA", target = "CLOSED")
    @Mapping(target = "status", ignore = true)
    SessaoResponse toResponse(Sessao domain);

    @AfterMapping
    default void defineStatus(@MappingTarget SessaoResponse response, Sessao sessao) {
        response.setStatus(StatusSessao.valueOf(sessao.statusAtual().name()));
    }
}