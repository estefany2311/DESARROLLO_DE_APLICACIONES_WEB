package com.tecsup.huesped.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 400: fallan las validaciones del DTO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));

        Map<String, Object> cuerpo = crearCuerpo(HttpStatus.BAD_REQUEST, "Datos invalidos");
        cuerpo.put("errores", errores);
        return ResponseEntity.badRequest().body(cuerpo);
    }

    // 400: el JSON esta mal escrito o tiene un tipo de dato incorrecto
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> manejarJsonInvalido(HttpMessageNotReadableException ex) {
        return responder(HttpStatus.BAD_REQUEST, "El cuerpo de la solicitud no es valido");
    }

    // 400: el id de la URL no es un numero, por ejemplo /api/huespedes/abc
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> manejarParametroInvalido(MethodArgumentTypeMismatchException ex) {
        return responder(HttpStatus.BAD_REQUEST, "El parametro '" + ex.getName() + "' no es valido");
    }

    // 404: el recurso no existe
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarNoEncontrado(RecursoNoEncontradoException ex) {
        return responder(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // 409: el documento ya esta registrado
    @ExceptionHandler(RecursoDuplicadoException.class)
    public ResponseEntity<Map<String, Object>> manejarDuplicado(RecursoDuplicadoException ex) {
        return responder(HttpStatus.CONFLICT, ex.getMessage());
    }

    // 409: la base de datos rechaza la operacion, por ejemplo por la columna unica
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> manejarIntegridad(DataIntegrityViolationException ex) {
        return responder(HttpStatus.CONFLICT, "La operacion viola una restriccion de la base de datos");
    }

    private ResponseEntity<Map<String, Object>> responder(HttpStatus estado, String mensaje) {
        return ResponseEntity.status(estado).body(crearCuerpo(estado, mensaje));
    }

    private Map<String, Object> crearCuerpo(HttpStatus estado, String mensaje) {
        Map<String, Object> cuerpo = new LinkedHashMap<>();
        cuerpo.put("estado", estado.value());
        cuerpo.put("mensaje", mensaje);
        return cuerpo;
    }
}
