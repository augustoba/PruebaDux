package com.dux.pruebatecnica.controller;

import com.dux.pruebatecnica.model.Equipo;
import com.dux.pruebatecnica.service.EquipoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class EquipoController {
    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @PostMapping("/equipos")
    public ResponseEntity<Object> crearEquipo(@Valid  @RequestBody Equipo equipo,  BindingResult result) {
          if (result.hasErrors()) {
            List<String> errorList = result.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);
        }
        try {
            Equipo equipoObj = equipoService.crearEquipo(equipo);
            return ResponseEntity.status(HttpStatus.CREATED).body(equipoObj);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La solicitud es inválida");
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
            return ResponseEntity.status(HttpStatus.OK).body(equipo.get());
        }else{
            Map<String, Object> respuesta = new LinkedHashMap<>();
            respuesta.put("mensaje","Equipo no encontrado");
            respuesta.put("codigo",HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

}



