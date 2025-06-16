package com.compromissos.gerenciadorcompromissos.controller;

import java.time.LocalDate;

public record CreateCompromissoDto(LocalDate data, String hora, String descricao, String local) {}

