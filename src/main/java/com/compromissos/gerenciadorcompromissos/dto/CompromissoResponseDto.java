package com.compromissos.gerenciadorcompromissos.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CompromissoResponseDto( //Resposta
        UUID compromissoId,
        String titulo,
        String descricao,
        LocalDateTime dataHora,
        String cep,
        String cidade,
        String estado,
        Integer alertaDiasAntes,
        Long idTelegram,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {}