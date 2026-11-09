package com.tecsup.huesped.controller;

import com.tecsup.huesped.dto.HuespedDTO;
import com.tecsup.huesped.model.Huesped;
import com.tecsup.huesped.services.HuespedService;
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
@RequestMapping("/api/huespedes")
public class HuespedController {

    @Autowired
    private HuespedService service;

    @GetMapping
    public ResponseEntity<List<Huesped>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Huesped> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<Huesped> crear(@Valid @RequestBody HuespedDTO dto) {
        return ResponseEntity.status(201).body(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Huesped> actualizar(@PathVariable Long id,
                                              @Valid @RequestBody HuespedDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
