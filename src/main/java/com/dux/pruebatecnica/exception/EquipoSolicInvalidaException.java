package com.dux.pruebatecnica.exception;

import org.springframework.http.HttpStatus;

import java.util.LinkedHashMap;
import java.util.Map;

public class EquipoSolicInvalidaException extends RuntimeException {

    public EquipoSolicInvalidaException() {
        super("La solicitud es inválida.");
    }

    public Map<String, Object> SolicInvalida() {
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("mensaje", getMessage());
        error.put("codigo", HttpStatus.BAD_REQUEST.value());
        return error;
    }
}
