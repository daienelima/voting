package com.cooperative.voting.infrastructure.adapter.out.persistence.mapper;

import com.cooperative.voting.domain.model.Pauta;
import com.cooperative.voting.infrastructure.adapter.out.persistence.entity.PautaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PautaEntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "titulo", source = "titulo")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "dataCriacao", source = "dataCriacao")
    Pauta toDomain(PautaEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "titulo", source = "titulo")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "dataCriacao", source = "dataCriacao")
    PautaEntity toEntity(Pauta domain);
}
