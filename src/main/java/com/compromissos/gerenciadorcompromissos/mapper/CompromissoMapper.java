package com.compromissos.gerenciadorcompromissos.mapper;

import com.compromissos.gerenciadorcompromissos.dto.CompromissoDto;
import com.compromissos.gerenciadorcompromissos.dto.CompromissoResponseDto;
import com.compromissos.gerenciadorcompromissos.dto.CompromissoUpdateDto;
import com.compromissos.gerenciadorcompromissos.entity.CompromissoEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompromissoMapper {


    CompromissoEntity toEntity(CompromissoDto dto);

    CompromissoResponseDto toResponseDto(CompromissoEntity entity);

    // Atualização parcial: MapStruct permite isso com @BeanMapping e ignoreByDefault
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(CompromissoUpdateDto dto, @MappingTarget CompromissoEntity entity);
}
