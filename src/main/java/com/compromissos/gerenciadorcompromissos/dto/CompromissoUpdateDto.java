package com.compromissos.gerenciadorcompromissos.dto;

import java.time.LocalDateTime;

public record CompromissoUpdateDto(
        String titulo,
        String descricao,
        LocalDateTime dataHora,
        String cep,
        String cidade,
        String estado,
        Integer alertaDiasAntes
) {}
