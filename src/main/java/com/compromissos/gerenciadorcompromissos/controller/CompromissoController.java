package com.compromissos.gerenciadorcompromissos.controller;

import com.compromissos.gerenciadorcompromissos.entity.CompromissoEntity;
import com.compromissos.gerenciadorcompromissos.service.CompromissoService;
import com.compromissos.gerenciadorcompromissos.utils.exceptions.CompromissoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/compromissos")
public class CompromissoController {

    private CompromissoService compromissoService;

    public CompromissoController(CompromissoService compromissoService) {
        this.compromissoService = compromissoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompromissoEntity createCompromisso(@RequestBody CreateCompromissoDto createCompromissoDto) {
        var compromissoSaved = compromissoService.createCompromisso(createCompromissoDto);
        return compromissoSaved;
    }

    @GetMapping("/{compromissoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CompromissoEntity> getCompromissoById(@PathVariable("compromissoId") String compromissoId) {

        var compromisso = compromissoService.getCompromissoById(compromissoId);

        if(compromisso.isPresent()){

            return ResponseEntity.ok(compromisso.get());

        } else {
            throw new CompromissoNaoEncontradoException(compromissoId);
        }
    }

    @GetMapping
    public ResponseEntity<CompromissoEntity> getTodosCompromissos(@PathVariable("compromissoId") String compromissoId) {

        //
        return null;
    }

    @GetMapping("/dia/{data}")
    public ResponseEntity<CompromissoEntity> getTodosCompromissosDia(@PathVariable("data") String data) {
        //
        return null;
    }

    @GetMapping("/mes/{data}")
    public ResponseEntity<CompromissoEntity> getTodosCompromissosMes(@PathVariable("data") String data) {
        //
        return null;
    }

}
