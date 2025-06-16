package com.compromissos.gerenciadorcompromissos.repository;

import com.compromissos.gerenciadorcompromissos.entity.CompromissoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CompromissoRepository extends JpaRepository<CompromissoEntity, UUID> {


    List<CompromissoEntity> findByData(LocalDate data);

    @Query("SELECT c FROM CompromissoEntity c WHERE MONTH(c.data) = :mes AND YEAR(c.data) = :ano")
    List<CompromissoEntity> findByMesEAno(int mes, int ano);
}
