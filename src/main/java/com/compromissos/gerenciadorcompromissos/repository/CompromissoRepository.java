package com.compromissos.gerenciadorcompromissos.repository;

import com.compromissos.gerenciadorcompromissos.entity.CompromissoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompromissoRepository extends JpaRepository<CompromissoEntity, UUID> {

    // 1. Buscar compromissos por data exata (ex: 2025-10-01)
    List<CompromissoEntity> findByDataHoraBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

    // 2. Buscar compromissos por mês (ex: 2025-10)
    @Query("SELECT c FROM CompromissoEntity c WHERE FUNCTION('MONTH', c.dataHora) = :month AND FUNCTION('YEAR', c.dataHora) = :year")
    List<CompromissoEntity> findByMesEAno(int month, int year);

    // 3. Buscar compromissos por cidade
    List<CompromissoEntity> findByCidadeIgnoreCase(String cidade);

    // 4. Buscar compromissos por estado (UF)
    List<CompromissoEntity> findByEstadoIgnoreCase(String estado);

    // 5. Buscar compromissos por CEP
    List<CompromissoEntity> findByCep(String cep);

    // 6. Buscar compromissos por ID do Telegram
    List<CompromissoEntity> findByIdTelegram(Long idTelegram);

    // 7. Buscar compromissos com data específica para envio de alerta
    @Query("SELECT c FROM CompromissoEntity c WHERE c.dataHora BETWEEN :inicio AND :fim")
    List<CompromissoEntity> findCompromissosNoPeriodo(LocalDateTime inicio, LocalDateTime fim);

}
