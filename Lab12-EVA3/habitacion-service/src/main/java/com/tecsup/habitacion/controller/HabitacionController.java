package com.tecsup.habitacion.controller;

import com.tecsup.habitacion.dto.HabitacionDTO;
import com.tecsup.habitacion.model.Habitacion;
import com.tecsup.habitacion.services.HabitacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionService service;

    @GetMapping
    public ResponseEntity<List<Habitacion>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<Habitacion> crear(@Valid @RequestBody HabitacionDTO dto) {
        return ResponseEntity.status(201).body(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habitacion> actualizar(@PathVariable Long id,
                                                 @Valid @RequestBody HabitacionDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
