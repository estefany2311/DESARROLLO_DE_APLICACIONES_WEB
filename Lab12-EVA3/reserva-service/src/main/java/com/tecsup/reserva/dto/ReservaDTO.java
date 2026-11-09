package com.tecsup.reserva.dto;

import com.tecsup.reserva.model.EstadoReserva;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class ReservaDTO {

    @NotNull(message = "El idHuesped es obligatorio")
    @Positive(message = "El idHuesped debe ser mayor a 0")
    private Long idHuesped;

    @NotNull(message = "El idHabitacion es obligatorio")
    @Positive(message = "El idHabitacion debe ser mayor a 0")
    private Long idHabitacion;

    @NotNull(message = "La fecha de entrada es obligatoria")
    private LocalDate fechaEntrada;

    @NotNull(message = "La fecha de salida es obligatoria")
    private LocalDate fechaSalida;

    // Opcional: al crear, si no se envia, la reserva queda PENDIENTE
    private EstadoReserva estado;

    public Long getIdHuesped() {
        return idHuesped;
    }

    public void setIdHuesped(Long idHuesped) {
        this.idHuesped = idHuesped;
    }

    public Long getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(Long idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }
}
