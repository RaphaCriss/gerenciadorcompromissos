package com.compromissos.gerenciadorcompromissos.controller;

import com.compromissos.gerenciadorcompromissos.dto.CompromissoDto;
import com.compromissos.gerenciadorcompromissos.dto.CompromissoResponseDto;
import com.compromissos.gerenciadorcompromissos.dto.CompromissoUpdateDto;
import com.compromissos.gerenciadorcompromissos.service.CompromissoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/compromissos")
@RequiredArgsConstructor
public class CompromissoController {

    private final CompromissoService service;

    // Criar compromisso
    @PostMapping
    public ResponseEntity<CompromissoResponseDto> criar(@Valid @RequestBody CompromissoDto dto) {
        var response = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<CompromissoResponseDto> buscarPorId(@PathVariable UUID id) {
        var response = service.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    // Atualizar compromisso
    @PutMapping("/{id}")
    public ResponseEntity<CompromissoResponseDto> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody CompromissoUpdateDto dto
    ) {
        var response = service.atualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    // Deletar compromisso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Listar por data (yyyy-MM-dd)
    @GetMapping("/data")
    public ResponseEntity<List<CompromissoResponseDto>> listarPorData(
            @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data
    ) {
        var lista = service.listarPorData(data);
        return ResponseEntity.ok(lista);
    }

    // Listar por mês e ano
    @GetMapping("/mes-ano")
    public ResponseEntity<List<CompromissoResponseDto>> listarPorMesAno(
            @RequestParam int mes,
            @RequestParam int ano
    ) {
        var lista = service.listarPorMesAno(mes, ano);
        return ResponseEntity.ok(lista);
    }

    // Listar por cidade
    @GetMapping("/cidade")
    public ResponseEntity<List<CompromissoResponseDto>> listarPorCidade(
            @RequestParam String cidade
    ) {
        var lista = service.listarPorCidade(cidade);
        return ResponseEntity.ok(lista);
    }

    // Listar por estado
    @GetMapping("/estado")
    public ResponseEntity<List<CompromissoResponseDto>> listarPorEstado(
            @RequestParam String estado
    ) {
        var lista = service.listarPorEstado(estado);
        return ResponseEntity.ok(lista);
    }

    // Listar por CEP
    @GetMapping("/cep")
    public ResponseEntity<List<CompromissoResponseDto>> listarPorCep(
            @RequestParam String cep
    ) {
        var lista = service.listarPorCep(cep);
        return ResponseEntity.ok(lista);
    }

    // Listar por idTelegram
    @GetMapping("/telegram")
    public ResponseEntity<List<CompromissoResponseDto>> listarPorTelegram(
            @RequestParam Long idTelegram
    ) {
        var lista = service.listarPorTelegram(idTelegram);
        return ResponseEntity.ok(lista);
    }
}
