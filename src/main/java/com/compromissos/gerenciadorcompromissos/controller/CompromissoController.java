package com.compromissos.gerenciadorcompromissos.controller;

import com.compromissos.gerenciadorcompromissos.entity.CompromissoEntity;
import com.compromissos.gerenciadorcompromissos.service.CompromissoService;
import com.compromissos.gerenciadorcompromissos.utils.exceptions.CompromissoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/compromissos")
public class CompromissoController {

    private final CompromissoService compromissoService;

    public CompromissoController(CompromissoService compromissoService) {
        this.compromissoService = compromissoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompromissoEntity createCompromisso(@RequestBody CreateCompromissoDto createCompromissoDto) {
        return compromissoService.createCompromisso(createCompromissoDto);
    }

    @GetMapping("/{compromissoId}")
    public ResponseEntity<CompromissoEntity> getCompromissoById(@PathVariable("compromissoId") UUID compromissoId) {
        var compromisso = compromissoService.getCompromissoById(compromissoId);
        return compromisso.map(ResponseEntity::ok)
                .orElseThrow(() -> new CompromissoNaoEncontradoException(compromissoId));
    }

    @GetMapping
    public ResponseEntity<List<CompromissoEntity>> getAllCompromissos() {
        List<CompromissoEntity> compromissos = compromissoService.getTodosCompromissos();
        return ResponseEntity.ok(compromissos);
    }

}
