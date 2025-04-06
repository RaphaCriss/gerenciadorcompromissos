package com.compromissos.gerenciadorcompromissos.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_compromissos")
public class CompromissoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID compromissoId;

    @Column(name = "data")
    private String data;

    @Column(name = "hora")
    private String hora;

    @Column(name = "decricao")
    private String descricao;

    @Column(name = "local")
    private String local;

//    @Column(name = "idTelegram", unique = true)
//    private String idTelegram;

    @CreationTimestamp
    private Instant creationTimestamp;

    @UpdateTimestamp
    private Instant updateTimestamp;

    public CompromissoEntity() {

    }

    public CompromissoEntity(UUID compromissoId, String data, String hora, String descricao, String local, Instant creationTimestamp, Instant updateTimestamp) {
        this.compromissoId = compromissoId;
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
        this.local = local;
        this.creationTimestamp = creationTimestamp;
        this.updateTimestamp = updateTimestamp;
    }

    public UUID getCompromissoId() {
        return compromissoId;
    }

    public void setCompromissoId(UUID compromissoId) {
        this.compromissoId = compromissoId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {this.local = local; }

    public void setCreationTimestamp(Instant creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public Instant getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Instant updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
}
