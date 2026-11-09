package com.tecsup.habitacion.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class HabitacionDTO {

    @NotBlank(message = "El numero de habitacion es obligatorio")
    @Pattern(regexp = "^[A-Za-z0-9-]{1,10}$",
            message = "El numero debe tener hasta 10 letras, numeros o guiones")
    private String numero;

    @NotBlank(message = "El tipo es obligatorio")
    @Pattern(regexp = "^(SIMPLE|DOBLE|MATRIMONIAL|SUITE)$",
            message = "El tipo debe ser SIMPLE, DOBLE, MATRIMONIAL o SUITE")
    private String tipo;

    @NotNull(message = "El precio por noche es obligatorio")
    @Positive(message = "El precio por noche debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2,
            message = "El precio debe tener hasta 8 enteros y 2 decimales")
    private BigDecimal precioPorNoche;

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 1, message = "La capacidad debe ser al menos 1")
    private Integer capacidad;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(BigDecimal precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
}
