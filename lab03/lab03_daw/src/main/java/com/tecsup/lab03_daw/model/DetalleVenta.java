package com.tecsup.lab03_daw.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Long idDetalle;

    private Integer cantidad;
    private Double precio;
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    public DetalleVenta() {
    }

    public DetalleVenta(Long idDetalle, Integer cantidad, Double precio, Double subtotal, Venta venta, Producto producto) {
        this.idDetalle = idDetalle;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = subtotal;
        this.venta = venta;
        this.producto = producto;
    }

    public Long getIdDetalle() { return idDetalle; }
    public void setIdDetalle(Long idDetalle) { this.idDetalle = idDetalle; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }

    public Venta getVenta() { return venta; }
    public void setVenta(Venta venta) { this.venta = venta; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
}