package com.dux.pruebatecnica.controller;

import com.dux.pruebatecnica.model.Equipo;
import com.dux.pruebatecnica.service.EquipoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EquipoController {
    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @PostMapping("/equipos")
    public ResponseEntity<Object> crearEquipo(@Valid  @RequestBody Equipo equipo,  BindingResult result) {
        System.out.println(equipo.getLiga() + equipo.getPais() + "asdsad");
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

    }

