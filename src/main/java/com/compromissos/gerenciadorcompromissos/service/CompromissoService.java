package com.compromissos.gerenciadorcompromissos.service;

import com.compromissos.gerenciadorcompromissos.controller.CompromissoDto;
import com.compromissos.gerenciadorcompromissos.entity.CompromissoEntity;
import com.compromissos.gerenciadorcompromissos.mapper.CompromissoMapper;
import com.compromissos.gerenciadorcompromissos.repository.CompromissoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompromissoService {

    @Autowired
    private CompromissoRepository compromissoRepository;

    @Autowired
    private CompromissoMapper compromissoMapper;

    public CompromissoService(CompromissoRepository compromissoRepository) {
        this.compromissoRepository = compromissoRepository;
    }

    public CompromissoEntity createCompromisso(CompromissoDto createCompromissoDto) {
        CompromissoEntity entity = compromissoMapper.toEntity(createCompromissoDto);
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
