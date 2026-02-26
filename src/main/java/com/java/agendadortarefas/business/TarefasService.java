package com.java.agendadortarefas.business;

import com.java.agendadortarefas.business.dto.TarefasDTO;
import com.java.agendadortarefas.business.mapper.TarefaConverter;
import com.java.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.java.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.java.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.java.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefa(String token, TarefasDTO dto) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefaConverter.paraTarefasEntity(dto);

        return tarefaConverter.paraTarefasDTO(
                tarefasRepository.save(entity));
    }
}
