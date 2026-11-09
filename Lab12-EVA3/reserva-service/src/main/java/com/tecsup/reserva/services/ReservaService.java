package com.tecsup.reserva.services;

import com.tecsup.reserva.dto.ReservaDTO;
import com.tecsup.reserva.exception.FechasInvalidasException;
import com.tecsup.reserva.exception.RecursoNoEncontradoException;
import com.tecsup.reserva.model.EstadoReserva;
import com.tecsup.reserva.model.Reserva;
import com.tecsup.reserva.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repo;

    @Autowired
    private ReferenciasClient referencias;

    public List<Reserva> listar() {
        return repo.findAll();
    }

    public Reserva obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe una reserva con id " + id));
    }

    public Reserva crear(ReservaDTO dto) {
        validarReglas(dto);
        Reserva reserva = new Reserva();
        copiarDatos(dto, reserva);
        // Si no se indica un estado, toda reserva nueva empieza PENDIENTE
        reserva.setEstado(dto.getEstado() != null ? dto.getEstado() : EstadoReserva.PENDIENTE);
        return repo.save(reserva);
    }

    public Reserva actualizar(Long id, ReservaDTO dto) {
        Reserva reserva = obtener(id);
        validarReglas(dto);
        copiarDatos(dto, reserva);
        // Si no se indica un estado, se conserva el que ya tenia
        if (dto.getEstado() != null) {
            reserva.setEstado(dto.getEstado());
        }
        return repo.save(reserva);
    }

    public void eliminar(Long id) {
        Reserva reserva = obtener(id);
        repo.delete(reserva);
    }

    private void validarReglas(ReservaDTO dto) {
        // Primero las fechas: si estan mal, no hace falta llamar a otros servicios
        if (!dto.getFechaSalida().isAfter(dto.getFechaEntrada())) {
            throw new FechasInvalidasException(
                    "La fecha de salida debe ser posterior a la fecha de entrada");
        }
        referencias.verificarHuesped(dto.getIdHuesped());
        referencias.verificarHabitacion(dto.getIdHabitacion());
    }

    private void copiarDatos(ReservaDTO dto, Reserva reserva) {
        reserva.setIdHuesped(dto.getIdHuesped());
        reserva.setIdHabitacion(dto.getIdHabitacion());
        reserva.setFechaEntrada(dto.getFechaEntrada());
        reserva.setFechaSalida(dto.getFechaSalida());
    }
}
