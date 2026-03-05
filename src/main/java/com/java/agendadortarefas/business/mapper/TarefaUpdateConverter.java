package com.java.agendadortarefas.business.mapper;

import com.java.agendadortarefas.business.dto.TarefasDTORecord;
import com.java.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.apache.commons.lang3.ObjectUtils;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateTarefas(TarefasDTORecord dto, @MappingTarget TarefasEntity entity);
}
