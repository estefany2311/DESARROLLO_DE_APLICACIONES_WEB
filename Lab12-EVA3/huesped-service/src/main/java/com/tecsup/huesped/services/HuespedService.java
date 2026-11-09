package com.tecsup.huesped.services;

import com.tecsup.huesped.dto.HuespedDTO;
import com.tecsup.huesped.exception.RecursoDuplicadoException;
import com.tecsup.huesped.exception.RecursoNoEncontradoException;
import com.tecsup.huesped.model.Huesped;
import com.tecsup.huesped.repository.HuespedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HuespedService {

    @Autowired
    private HuespedRepository repo;

    public List<Huesped> listar() {
        return repo.findAll();
    }

    public Huesped obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe un huesped con id " + id));
    }

    public Huesped crear(HuespedDTO dto) {
        if (repo.existsByDocumento(dto.getDocumento())) {
            throw new RecursoDuplicadoException(
                    "Ya existe un huesped con el documento " + dto.getDocumento());
        }
        Huesped huesped = new Huesped();
        copiarDatos(dto, huesped);
        return repo.save(huesped);
    }

    public Huesped actualizar(Long id, HuespedDTO dto) {
        Huesped huesped = obtener(id);
        if (repo.existsByDocumentoAndIdNot(dto.getDocumento(), id)) {
            throw new RecursoDuplicadoException(
                    "Ya existe otro huesped con el documento " + dto.getDocumento());
        }
        copiarDatos(dto, huesped);
        return repo.save(huesped);
    }

    public void eliminar(Long id) {
        Huesped huesped = obtener(id);
        repo.delete(huesped);
    }

    private void copiarDatos(HuespedDTO dto, Huesped huesped) {
        huesped.setNombres(dto.getNombres());
        huesped.setApellidos(dto.getApellidos());
        huesped.setDocumento(dto.getDocumento());
        huesped.setCorreo(dto.getCorreo());
        huesped.setTelefono(dto.getTelefono());
    }
}
