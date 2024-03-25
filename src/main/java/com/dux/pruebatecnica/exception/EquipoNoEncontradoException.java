package com.dux.pruebatecnica.exception;

import org.springframework.http.HttpStatus;
import java.util.LinkedHashMap;
import java.util.Map;

public class EquipoNoEncontradoException  extends RuntimeException  {

    public EquipoNoEncontradoException() {
        super("equipo no encontrado");
    }
    public Map<String, Object> noEncontrado() {
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("mensaje", getMessage());
        error.put("codigo", HttpStatus.NOT_FOUND.value());
        return error;
    }

}
