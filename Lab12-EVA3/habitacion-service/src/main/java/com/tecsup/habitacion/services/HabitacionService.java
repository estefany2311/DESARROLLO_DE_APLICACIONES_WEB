package com.tecsup.habitacion.services;

import com.tecsup.habitacion.dto.HabitacionDTO;
import com.tecsup.habitacion.exception.RecursoDuplicadoException;
import com.tecsup.habitacion.exception.RecursoNoEncontradoException;
import com.tecsup.habitacion.model.Habitacion;
import com.tecsup.habitacion.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository repo;

    public List<Habitacion> listar() {
        return repo.findAll();
    }

    public Habitacion obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe una habitacion con id " + id));
    }

    public Habitacion crear(HabitacionDTO dto) {
        if (repo.existsByNumero(dto.getNumero())) {
            throw new RecursoDuplicadoException(
                    "Ya existe una habitacion con el numero " + dto.getNumero());
        }
        Habitacion habitacion = new Habitacion();
        copiarDatos(dto, habitacion);
        return repo.save(habitacion);
    }

    public Habitacion actualizar(Long id, HabitacionDTO dto) {
        Habitacion habitacion = obtener(id);
        if (repo.existsByNumeroAndIdNot(dto.getNumero(), id)) {
            throw new RecursoDuplicadoException(
                    "Ya existe otra habitacion con el numero " + dto.getNumero());
        }
        copiarDatos(dto, habitacion);
        return repo.save(habitacion);
    }

    public void eliminar(Long id) {
        Habitacion habitacion = obtener(id);
        repo.delete(habitacion);
    }

    private void copiarDatos(HabitacionDTO dto, Habitacion habitacion) {
        habitacion.setNumero(dto.getNumero());
        habitacion.setTipo(dto.getTipo());
        habitacion.setPrecioPorNoche(dto.getPrecioPorNoche());
        habitacion.setCapacidad(dto.getCapacidad());
    }
}
