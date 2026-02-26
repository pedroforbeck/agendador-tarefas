package com.java.agendadortarefas.infrastructure.entity;

import com.java.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("tarefa")
public class TarefasEntity {
    private String id;
    private String nomeTarefa;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEvento;
    private String emailUsuario;
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum statusNotificacaoEnum;
}
