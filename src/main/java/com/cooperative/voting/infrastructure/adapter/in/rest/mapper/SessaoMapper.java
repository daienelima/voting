package com.cooperative.voting.infrastructure.adapter.in.rest.mapper;

import com.cooperative.voting.domain.model.Sessao;
import com.cooperative.voting.domain.model.enums.StatusSessao;
import com.yourcompany.voting.api.model.SessaoResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", imports = {StatusSessao.class})
public interface SessaoMapper {

    @Mapping(target = "status", ignore = true)
    SessaoResponse toResponse(Sessao domain);

    @AfterMapping
    default void defineStatus(@MappingTarget SessaoResponse response, Sessao sessao) {
        StatusSessao domainStatus = sessao.statusAtual();
        com.yourcompany.voting.api.model.StatusSessao apiStatus = switch (domainStatus) {
            case ABERTA -> com.yourcompany.voting.api.model.StatusSessao.OPEN;
            case ENCERRADA -> com.yourcompany.voting.api.model.StatusSessao.CLOSED;
        };
        response.setStatus(apiStatus);
    }
}