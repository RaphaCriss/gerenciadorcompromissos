package com.compromissos.gerenciadorcompromissos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record CompromissoDto( //Criação
        @NotBlank String titulo, //@NotBlank é usada para validar que um campo String não está nulo, vazio ou apenas com espaços em branco.
        String descricao,
        LocalDateTime dataHora,
        @Pattern(regexp = "\\d{5}-?\\d{3}") String cep, //Exatamente 5 dígitos (números), Um hífen opcional (- pode ou não existir), Exatamente 3 dígitos
        @NotBlank String cidade, //@NotBlank é usada para validar que um campo String não está nulo, vazio ou apenas com espaços em branco.
        @NotBlank String estado, //@NotBlank é usada para validar que um campo String não está nulo, vazio ou apenas com espaços em branco.
        @Min(2) Integer alertaDiasAntes, //Mínimo 2 dias
        Long idTelegram
){}
