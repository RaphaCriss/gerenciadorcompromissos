package com.compromissos.gerenciadorcompromissos.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_compromissos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompromissoEntity {

    @Id
    @Column(name = "compromisso_id", length = 36)
    private String compromissoId;

    @Column(nullable = false)
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Column(length = 8, nullable = false)
    private String cep;

    @Column(length = 100, nullable = false)
    private String cidade;

    @Column(length = 2, nullable = false)
    private String estado;

    @Column(name = "alerta_dias_antes", nullable = false)
    private Integer alertaDiasAntes;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @PrePersist
    public void prePersist() {
        if (this.compromissoId == null) {
            this.compromissoId = UUID.randomUUID().toString();
        }
        this.criadoEm = LocalDateTime.now();
        this.atualizadoEm = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.atualizadoEm = LocalDateTime.now();
    }

    @Column(name = "id_telegram", nullable = false)
    private Long idTelegram;

    @CreationTimestamp
    private Instant creationTimestamp;

}
