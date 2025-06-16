package com.compromissos.gerenciadorcompromissos.service;

import com.compromissos.gerenciadorcompromissos.controller.CreateCompromissoDto;
import com.compromissos.gerenciadorcompromissos.entity.CompromissoEntity;
import com.compromissos.gerenciadorcompromissos.repository.CompromissoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompromissoService {

    private final CompromissoRepository compromissoRepository;

    public CompromissoService(CompromissoRepository compromissoRepository) {
        this.compromissoRepository = compromissoRepository;
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

    public Optional<CompromissoEntity> getCompromissoById(UUID id) {
        return compromissoRepository.findById(id);
    }

    public List<CompromissoEntity> getTodosCompromissos() {
        return compromissoRepository.findAll();
    }

    public List<CompromissoEntity> getCompromissosPorDia(LocalDate data) {
        return compromissoRepository.findByData(data);
    }

    public List<CompromissoEntity> getCompromissosPorMes(int mes, int ano) {
        return compromissoRepository.findByMesEAno(mes, ano);
    }
}
