package com.java.agendadortarefas.business.mapper;

import com.java.agendadortarefas.business.dto.TarefasDTO;
import com.java.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    TarefasEntity paraTarefasEntity(TarefasDTO dto);

    TarefasDTO paraTarefasDTO(TarefasEntity entity);
}