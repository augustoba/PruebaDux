package com.dux.pruebatecnica.dto.mapper;

import com.dux.pruebatecnica.dto.request.EquipoRequestDTO;
import com.dux.pruebatecnica.dto.response.EquipoResponseDTO;
import com.dux.pruebatecnica.model.Equipo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface EquipoMapper {
            @Mapping(target = "nombre", source = "equipo")
            @Mapping(target = "liga.nombre", source = "liga")
            @Mapping(target = "pais.nombre", source = "pais")

        Equipo toEquipoEntity(EquipoRequestDTO equipoRequestDTO);
            @Mapping(target = "id", source = "id")
            @Mapping(target = "equipo", source = "nombre")
            @Mapping(target = "liga", source = "liga.nombre")
            @Mapping(target = "pais", source = "pais.nombre")

        EquipoResponseDTO toEquipoResponseDTO(Equipo equipo);
    List<EquipoResponseDTO> toEquipoResponseDTOList(List<Equipo> equipos);
}
