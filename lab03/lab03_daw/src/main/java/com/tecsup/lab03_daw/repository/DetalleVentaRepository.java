// DetalleVentaRepository.java
package com.tecsup.lab03_daw.repository;

import com.tecsup.lab03_daw.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
}