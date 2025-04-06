package com.compromissos.gerenciadorcompromissos.service;

import com.compromissos.gerenciadorcompromissos.controller.CreateCompromissoDto;
import com.compromissos.gerenciadorcompromissos.entity.CompromissoEntity;
import com.compromissos.gerenciadorcompromissos.repository.CompromissoRepository;
import com.compromissos.gerenciadorcompromissos.utils.exceptions.CompromissoJaExistenteException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;


@Service
public class CompromissoService {

    private CompromissoRepository compromissoRepository;

    public CompromissoService(CompromissoRepository compromissoRepository) {
        this.compromissoRepository = compromissoRepository;
    }


    public CompromissoEntity createCompromisso(CreateCompromissoDto createCompromissoDto) {

        //DTO -> ENTITY
        var entity = new CompromissoEntity(
                UUID.randomUUID(),
                createCompromissoDto.data(),
                createCompromissoDto.hora(),
                createCompromissoDto.descricao(),
                createCompromissoDto.local(),
                Instant.now(),
                null
        );

        if (compromissoRepository.existsById(entity.getCompromissoId())) {

            throw new CompromissoJaExistenteException(entity.getCompromissoId());
        }

        return compromissoRepository.save(entity);

    }

    public Optional<CompromissoEntity> getCompromissoById(String compromissoId){

        return compromissoRepository.findById(UUID.fromString(compromissoId));
    }

}
