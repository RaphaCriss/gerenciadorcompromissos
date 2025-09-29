package com.compromissos.gerenciadorcompromissos.service;

import com.compromissos.gerenciadorcompromissos.dto.CompromissoDto;
import com.compromissos.gerenciadorcompromissos.dto.CompromissoResponseDto;
import com.compromissos.gerenciadorcompromissos.dto.CompromissoUpdateDto;
import com.compromissos.gerenciadorcompromissos.entity.CompromissoEntity;
import com.compromissos.gerenciadorcompromissos.mapper.CompromissoMapper;
import com.compromissos.gerenciadorcompromissos.repository.CompromissoRepository;
import com.compromissos.gerenciadorcompromissos.utils.exceptions.CompromissoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompromissoService {

    private final CompromissoRepository repository;
    private final CompromissoMapper mapper;

    //  Criar novo compromisso
    public CompromissoResponseDto criar(CompromissoDto dto) {
        CompromissoEntity entity = mapper.toEntity(dto);
        CompromissoEntity salvo = repository.save(entity);
        return mapper.toResponseDto(salvo);
    }

    //  Buscar por ID
    public CompromissoResponseDto buscarPorId(UUID id) {
        CompromissoEntity entity = repository.findById(id)
                .orElseThrow(() -> new CompromissoNaoEncontradoException("Compromisso não encontrado"));
        return mapper.toResponseDto(entity);
    }

    //  Listar por data
    public List<CompromissoResponseDto> listarPorData(LocalDate data) {
        LocalDateTime inicio = data.atStartOfDay();
        LocalDateTime fim = data.atTime(23, 59, 59);
        return repository.findByDataHoraBetween(inicio, fim)
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    //  Listar por mês/ano
    public List<CompromissoResponseDto> listarPorMesAno(int mes, int ano) {
        return repository.findByMesEAno(mes, ano)
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    //  Listar por cidade
    public List<CompromissoResponseDto> listarPorCidade(String cidade) {
        return repository.findByCidadeIgnoreCase(cidade)
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    //  Listar por estado
    public List<CompromissoResponseDto> listarPorEstado(String estado) {
        return repository.findByEstadoIgnoreCase(estado)
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    //  Listar por CEP
    public List<CompromissoResponseDto> listarPorCep(String cep) {
        return repository.findByCep(cep)
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    //  Atualizar compromisso
    public CompromissoResponseDto atualizar(UUID id, CompromissoUpdateDto dto) {
        CompromissoEntity entity = repository.findById(id)
                .orElseThrow(() -> new CompromissoNaoEncontradoException("Compromisso não encontrado"));

        mapper.updateFromDto(dto, entity);
        CompromissoEntity atualizado = repository.save(entity);

        return mapper.toResponseDto(atualizado);
    }

    //  Deletar compromisso
    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new CompromissoNaoEncontradoException("Compromisso não encontrado");
        }
        repository.deleteById(id);
    }

    //  Listar todos de um usuário do Telegram
    public List<CompromissoResponseDto> listarPorTelegram(Long telegramId) {
        return repository.findByIdTelegram(telegramId)
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }
}
