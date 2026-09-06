// DetalleVentaController.java
package com.tecsup.lab03_daw.controller;

import com.tecsup.lab03_daw.model.DetalleVenta;
import com.tecsup.lab03_daw.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/detalles")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService service;

    @GetMapping
    public List<DetalleVenta> listar() { return service.listar(); }

    @PostMapping
    public ResponseEntity<DetalleVenta> guardar(@RequestBody DetalleVenta d) {
        return ResponseEntity.status(201).body(service.guardar(d));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> obtener(@PathVariable Long id) {
        DetalleVenta d = service.obtener(id);
        if (d == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(d);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        DetalleVenta d = service.obtener(id);
        if (d == null) return ResponseEntity.notFound().build();
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}