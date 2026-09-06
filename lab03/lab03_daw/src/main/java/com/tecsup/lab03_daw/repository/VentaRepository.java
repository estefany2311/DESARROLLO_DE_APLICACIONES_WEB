// VentaRepository.java
package com.tecsup.lab03_daw.repository;

import com.tecsup.lab03_daw.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}