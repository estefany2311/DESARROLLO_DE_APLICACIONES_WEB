package com.tecsup.lab03_daw.service;

import com.tecsup.lab03_daw.model.Cliente;
import com.tecsup.lab03_daw.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public List<Cliente> listar() { return repo.findAll(); }
    public Cliente guardar(Cliente c) { return repo.save(c); }
    public Cliente obtener(Long id) { return repo.findById(id).orElse(null); }
    public void eliminar(Long id) { repo.deleteById(id); }
}