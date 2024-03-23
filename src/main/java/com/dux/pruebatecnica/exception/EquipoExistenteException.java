package com.dux.pruebatecnica.exception;

import org.springframework.http.HttpStatus;

import java.util.LinkedHashMap;
import java.util.Map;

public class EquipoExistenteException extends RuntimeException {

    public EquipoExistenteException() {
        super("El equipo ya existe en la base de datos.");
    }

    public Map<String, Object> existeEnDB() {
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("mensaje", getMessage());
        error.put("codigo", HttpStatus.BAD_REQUEST.value());
        return error;
    }
}