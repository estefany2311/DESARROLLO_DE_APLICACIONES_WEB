package com.tecsup.lab03_daw.controller;

import com.tecsup.lab03_daw.model.Cliente;
import com.tecsup.lab03_daw.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public List<Cliente> listar() { return service.listar(); }

    @PostMapping
    public ResponseEntity<Cliente> guardar(@RequestBody Cliente c) {
        return ResponseEntity.status(201).body(service.guardar(c));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtener(@PathVariable Long id) {
        Cliente c = service.obtener(id);
        if (c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Long id, @RequestBody Cliente c) {
        Cliente existente = service.obtener(id);
        if (existente == null) return ResponseEntity.notFound().build();
        existente.setNombre(c.getNombre());
        existente.setApellido(c.getApellido());
        existente.setTelefono(c.getTelefono());
        existente.setEmail(c.getEmail());
        return ResponseEntity.ok(service.guardar(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Cliente c = service.obtener(id);
        if (c == null) return ResponseEntity.notFound().build();
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}