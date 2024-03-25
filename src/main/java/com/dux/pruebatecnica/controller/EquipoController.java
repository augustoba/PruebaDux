package com.dux.pruebatecnica.controller;

import com.dux.pruebatecnica.dto.mapper.EquipoMapper;
import com.dux.pruebatecnica.dto.request.EquipoRequestDTO;
import com.dux.pruebatecnica.dto.response.EquipoResponseDTO;
import com.dux.pruebatecnica.exception.EquipoException;
import com.dux.pruebatecnica.exception.EquipoExistenteException;
import com.dux.pruebatecnica.exception.EquipoNoEncontradoException;
import com.dux.pruebatecnica.model.Equipo;
import com.dux.pruebatecnica.service.EquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import static com.dux.pruebatecnica.exception.EquipoException.errorRespuesta;
import static com.dux.pruebatecnica.exception.EquipoException.respuestaErrorValid;

@RestController
@Tag(name = "Api gestión de equipos de fútbol--", description = "Controlador para gestionar equipos (crear, actualizar, eliminar, listar y buscar.)")
public class EquipoController {
    private final EquipoService equipoService;
    private final EquipoMapper equipoMapper;

    public EquipoController(EquipoService equipoService, EquipoMapper equipoMapper) {
        this.equipoService = equipoService;
        this.equipoMapper = equipoMapper;
    }
    @Operation(summary = "CREAR EQUIPOS")
    @PostMapping("/equipos")
    public ResponseEntity<Object> crearEquipo(@Valid @RequestBody EquipoRequestDTO equipoRequestDTO, BindingResult result) {

        if (equipoService.encontrarPorNombre(equipoRequestDTO.getNombre()) !=null ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorRespuesta("El equipo ya existe en la base de datos.",HttpStatus.BAD_REQUEST));
        }
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
    @Operation(summary = "LISTAR EQUIPOS")
    @GetMapping("/equipos")
    public ResponseEntity<Object> listarEquipos() {
        return ResponseEntity.status(HttpStatus.OK).body(equipoService.listarEquipos());
    }

    @GetMapping("/equipos/{id}")
    @Operation(summary = "ENCONTRAR EQUIPO POR ID")
    public ResponseEntity<Object> encontrarEquipoPorId(@PathVariable Integer id) {
        Optional<Equipo> equipo = equipoService.findEquipoById(id);

        if (equipo.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(equipoMapper.toEquipoResponseDTO(equipo.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRespuesta("Equipo no encontrado", HttpStatus.NOT_FOUND));
        }
    }
    @Operation(summary = "BUSCAR EQUIPOS POR NOMBRE")
    @GetMapping("/equipos/buscar")
    public ResponseEntity<Object> buscarEquiposPorNombre(@RequestParam("nombre") String nombre) {
        List<Equipo> equipos = equipoService.equiposListaNombres(nombre);
        if (equipos.isEmpty()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRespuesta("Equipo no encontrado",HttpStatus.NOT_FOUND));
        }
        return ResponseEntity.status(HttpStatus.OK).body(equipoMapper.toEquipoResponseDTOList(equipos));
    }

    @Operation(summary = "ELIMINAR EQUIPO POR ID")
    @DeleteMapping("/equipos/{id}")
    public ResponseEntity<Object> eliminarEquipo(@PathVariable Integer id) {
        try {
            equipoService.eliminarEquipo(id);
            return ResponseEntity.noContent().build();
        }catch (EquipoNoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.noEncontrado());
        }
    }
    @Operation(summary = "ACTUALIZAR EQUIPO")
    @PutMapping("/equipos/{id}")
    public ResponseEntity<Object> actualizarEquipo(@PathVariable Integer id, @Valid  @RequestBody EquipoRequestDTO equipoRequestDTO, BindingResult result) {

        Optional<Equipo> equipoExistente = equipoService.findEquipoById(id);
        if (!equipoExistente.isPresent()) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRespuesta("Equipo no encontrado",HttpStatus.NOT_FOUND));
        }
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EquipoException.respuestaErrorValid(result));
        }
        try {
            EquipoResponseDTO equipoActualizado = equipoService.actualizarEquipo(id, equipoRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(equipoActualizado);
        } catch (EquipoExistenteException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.existeEnDB());
        }
    }



}

