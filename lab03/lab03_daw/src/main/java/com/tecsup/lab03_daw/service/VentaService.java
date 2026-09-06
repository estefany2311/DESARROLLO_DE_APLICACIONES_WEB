// VentaService.java
package com.tecsup.lab03_daw.service;

import com.tecsup.lab03_daw.model.Venta;
import com.tecsup.lab03_daw.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VentaService {

    @Autowired
    private VentaRepository repo;

    public List<Venta> listar() { return repo.findAll(); }
    public Venta guardar(Venta v) { return repo.save(v); }
    public Venta obtener(Long id) { return repo.findById(id).orElse(null); }
    public void eliminar(Long id) { repo.deleteById(id); }
}