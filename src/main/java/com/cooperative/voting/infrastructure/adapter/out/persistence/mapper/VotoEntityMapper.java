package com.cooperative.voting.infrastructure.adapter.out.persistence.mapper;

import com.cooperative.voting.domain.model.Voto;
import com.cooperative.voting.infrastructure.adapter.out.persistence.entity.VotoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VotoEntityMapper {

    @Mapping(target = "dataHora", source = "createdAt")
    @Mapping(target = "escolha", expression = "java(TipoVoto.valueOf(entity.getEscolha()))")
    Voto toDomain(VotoEntity entity);

    @Mapping(target = "createdAt", source = "dataHora")
    @Mapping(target = "escolha", expression = "java(domain.escolha().name())")
    VotoEntity toEntity(Voto domain);

}