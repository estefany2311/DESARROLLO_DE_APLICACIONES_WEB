// VentaController.java
package com.tecsup.lab03_daw.controller;

import com.tecsup.lab03_daw.model.Venta;
import com.tecsup.lab03_daw.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService service;

    @GetMapping
    public List<Venta> listar() { return service.listar(); }

    @PostMapping
    public ResponseEntity<Venta> guardar(@RequestBody Venta v) {
        return ResponseEntity.status(201).body(service.guardar(v));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtener(@PathVariable Long id) {
        Venta v = service.obtener(id);
        if (v == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(v);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Venta v = service.obtener(id);
        if (v == null) return ResponseEntity.notFound().build();
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}