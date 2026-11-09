package com.tecsup.reserva.exception;

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

    // 400: la salida no es posterior a la entrada
    @ExceptionHandler(FechasInvalidasException.class)
    public ResponseEntity<Map<String, Object>> manejarFechas(FechasInvalidasException ex) {
        return responder(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // 400: JSON mal escrito, fecha con otro formato o estado que no existe
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> manejarJsonInvalido(HttpMessageNotReadableException ex) {
        return responder(HttpStatus.BAD_REQUEST,
                "El cuerpo no es valido. Las fechas usan el formato yyyy-MM-dd "
                        + "y el estado debe ser PENDIENTE, CONFIRMADA o CANCELADA");
    }

    // 400: el id de la URL no es un numero, por ejemplo /api/reservas/abc
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> manejarParametroInvalido(MethodArgumentTypeMismatchException ex) {
        return responder(HttpStatus.BAD_REQUEST, "El parametro '" + ex.getName() + "' no es valido");
    }

    // 404: no existe la reserva, el huesped o la habitacion
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarNoEncontrado(RecursoNoEncontradoException ex) {
        return responder(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // 503: no se pudo consultar otro microservicio
    @ExceptionHandler(ServicioNoDisponibleException.class)
    public ResponseEntity<Map<String, Object>> manejarServicioCaido(ServicioNoDisponibleException ex) {
        return responder(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
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
