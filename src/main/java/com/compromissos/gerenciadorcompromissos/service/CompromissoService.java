package com.compromissos.gerenciadorcompromissos.service;

import com.compromissos.gerenciadorcompromissos.controller.CreateCompromissoDto;
import com.compromissos.gerenciadorcompromissos.entity.CompromissoEntity;
import com.compromissos.gerenciadorcompromissos.repository.CompromissoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CompromissoService {

    private final CompromissoRepository compromissoRepository;

    public CompromissoService() {
        compromissoRepository = null;
    }

    public CompromissoEntity createCompromisso(CreateCompromissoDto createCompromissoDto) {

        var entity = CompromissoEntity.builder()
                .data(createCompromissoDto.data())
                .hora(createCompromissoDto.hora())
                .descricao(createCompromissoDto.descricao())
                .local(createCompromissoDto.local())
                .build();

        return compromissoRepository.save(entity);
    }

    public Optional<CompromissoEntity> getCompromissoById(String compromissoId) {
        return compromissoRepository.findById(UUID.fromString(compromissoId));
    }
}
