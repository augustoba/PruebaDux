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
import java.util.*;
import static com.dux.pruebatecnica.exception.EquipoException.errorRespuesta;
import static com.dux.pruebatecnica.exception.EquipoException.respuestaErrorValid;

@RestController
public class EquipoController {
    private final EquipoService equipoService;
    private final EquipoMapper equipoMapper;

    public EquipoController(EquipoService equipoService, EquipoMapper equipoMapper) {
        this.equipoService = equipoService;
        this.equipoMapper = equipoMapper;
    }

    @PostMapping("/equipos")
    public ResponseEntity<Object> crearEquipo(@Valid @RequestBody EquipoRequestDTO equipoRequestDTO, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaErrorValid(result));
        }
        try {
            EquipoResponseDTO equipoObj = equipoService.crearEquipo(equipoRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(equipoObj);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorRespuesta("La solicitud es inválida", HttpStatus.BAD_REQUEST));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorRespuesta(e.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }
    @GetMapping("/equipos")
    public ResponseEntity<Object> listarEquipos() {
        return ResponseEntity.status(HttpStatus.OK).body(equipoService.listarEquipos());
    }

    @GetMapping("/equipos/{id}")
    public ResponseEntity<Object> encontrarEquipoPorId(@PathVariable Integer idEquipo) {
        Optional<Equipo> equipo = equipoService.findEquipoById(idEquipo);

        if (equipo.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(equipoMapper.toEquipoResponseDTO(equipo.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRespuesta("Equipo no encontrado", HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/equipos/buscar")
    public ResponseEntity<Object> buscarEquiposPorNombre(@RequestParam("nombre") String nombre) {
        List<Equipo> equipos = equipoService.equiposListaNombres(nombre);
        if (equipos.isEmpty()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRespuesta("Equipo no encontrado",HttpStatus.NOT_FOUND));
        }
        return ResponseEntity.status(HttpStatus.OK).body(equipoMapper.toEquipoResponseDTOList(equipos));
    }

    @DeleteMapping("/equipos/{id}")
    public ResponseEntity<Object> eliminarEquipo(@PathVariable Integer id) {
        try {
            equipoService.eliminarEquipo(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRespuesta("Equipo no encontrado",HttpStatus.NOT_FOUND));
        }
    }



}

