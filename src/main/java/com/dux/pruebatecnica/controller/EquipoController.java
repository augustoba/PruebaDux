package com.dux.pruebatecnica.controller;

import com.dux.pruebatecnica.dto.mapper.EquipoMapper;
import com.dux.pruebatecnica.dto.request.EquipoRequestDTO;
import com.dux.pruebatecnica.dto.response.EquipoResponseDTO;
import com.dux.pruebatecnica.model.Equipo;
import com.dux.pruebatecnica.service.EquipoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.*;

@RestController
public class EquipoController {
    private final EquipoService equipoService;
    private final EquipoMapper equipoMapper;

    public EquipoController(EquipoService equipoService, EquipoMapper equipoMapper) {
        this.equipoService = equipoService;
        this.equipoMapper = equipoMapper;
    }

    @PostMapping("/equipos")
    public ResponseEntity<Object> crearEquipo(@Valid  @RequestBody EquipoRequestDTO equipoRequestDTO, BindingResult result) {

          if (result.hasErrors()) {
            String error = result.getFieldErrors().get(0).getDefaultMessage();
              Map<String, Object> respuesta = new LinkedHashMap<>();
              respuesta.put("mensaje",error);
              respuesta.put("codigo",HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }
        try {
            EquipoResponseDTO equipoObj = equipoService.crearEquipo(equipoRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(equipoObj);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La solicitud es inválida");
        } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    }

    @GetMapping("/equipos")
    public ResponseEntity<Object> listarEquipos() {
            return ResponseEntity.status(HttpStatus.OK).body(equipoService.listarEquipos());
    }

    @GetMapping("/equipos/{id}")
    public ResponseEntity<Object> encontrarEquipoPorId(@PathVariable Integer id) {
        Optional<Equipo> equipo = equipoService.findEquipoById(id);

        if (equipo.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(equipoMapper.toEquipoResponseDTO(equipo.get()));
        }else{
            Map<String, Object> respuesta = new LinkedHashMap<>();
            respuesta.put("mensaje","Equipo no encontrado");
            respuesta.put("codigo",HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

}



