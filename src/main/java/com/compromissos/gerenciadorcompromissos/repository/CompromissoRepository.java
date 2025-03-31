package com.compromissos.gerenciadorcompromissos.repository;

import com.compromissos.gerenciadorcompromissos.entity.Compromisso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompromissoRepository extends JpaRepository<Compromisso, UUID> {
}
