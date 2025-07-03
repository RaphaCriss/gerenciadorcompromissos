package com.compromissos.gerenciadorcompromissos.controller;

import java.time.LocalDate;

public record CompromissoDto(
        LocalDate data,
        String hora,
        String descricao,
        String local
) {
    
}
