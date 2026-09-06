package com.tecsup.lab03_daw.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long idVenta;

    private LocalDate fecha;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    public Venta() {
    }

    public Venta(Long idVenta, LocalDate fecha, Double total, Cliente cliente, Empleado empleado) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.total = total;
        this.cliente = cliente;
        this.empleado = empleado;
    }

    public Long getIdVenta() { return idVenta; }
    public void setIdVenta(Long idVenta) { this.idVenta = idVenta; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Empleado getEmpleado() { return empleado; }
    public void setEmpleado(Empleado empleado) { this.empleado = empleado; }
}