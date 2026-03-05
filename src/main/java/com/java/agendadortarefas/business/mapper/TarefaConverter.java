package com.java.agendadortarefas.business.mapper;

import com.java.agendadortarefas.business.dto.TarefasDTORecord;
import com.java.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "dataEvento", target = "dataEvento")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    TarefasEntity paraTarefasEntity(TarefasDTORecord dto);

    TarefasDTORecord paraTarefasDTORecord(TarefasEntity entity);

    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTORecord> dto);

    List<TarefasDTORecord> paraListaTarefasDTORecord(List<TarefasEntity> entities);
}