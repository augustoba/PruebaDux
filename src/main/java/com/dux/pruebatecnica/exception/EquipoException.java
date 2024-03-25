package com.dux.pruebatecnica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;


import java.util.LinkedHashMap;
import java.util.Map;

public class EquipoException  {



    public static Map<String, Object> errorRespuesta(String mensaje, HttpStatus status) {
        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("mensaje", mensaje);
        respuesta.put("codigo", status.value());
        return respuesta;
    }
    public static Map<String, Object> respuestaErrorValid(BindingResult result) {
        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("mensaje", result.getFieldErrors().get(0).getDefaultMessage());
        respuesta.put("codigo", HttpStatus.BAD_REQUEST.value());
        return respuesta;
    }
    public static Map<String, Object> respuestaErrorEquipoExistente() {
        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("mensaje", "El equipo ya existe en la base de datos.");
        respuesta.put("codigo", HttpStatus.BAD_REQUEST.value());
        return respuesta;
    }
}

