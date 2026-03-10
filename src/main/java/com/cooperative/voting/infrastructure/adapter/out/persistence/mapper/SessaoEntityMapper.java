package com.cooperative.voting.infrastructure.adapter.out.persistence.mapper;

import com.cooperative.voting.domain.model.Sessao;
import com.cooperative.voting.domain.model.SessaoVotacao;
import com.cooperative.voting.domain.model.enums.StatusSessao;
import com.cooperative.voting.infrastructure.adapter.out.persistence.entity.SessaoEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SessaoEntityMapper {

    @Mapping(target = "status", ignore = true)
    Sessao toDomain(SessaoEntity entity);

    @AfterMapping
    default void defineStatus(@MappingTarget Sessao sessao, SessaoEntity entity) {

        StatusSessao status = entity.getDataFechamento().isAfter(java.time.OffsetDateTime.now())
                ? StatusSessao.ABERTA
                : StatusSessao.ENCERRADA;

        sessao.setStatus(status);
    }

    @Mapping(target = "id", source = "id")
    @Mapping(target = "pautaId", source = "pautaId")
    @Mapping(target = "dataAbertura", source = "dataAbertura")
    @Mapping(target = "dataFechamento", source = "dataFechamento")
    SessaoEntity toEntity(Sessao domain);

    SessaoVotacao toDomainVotacao(SessaoEntity entity);
}