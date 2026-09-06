package com.tecsup.lab03_daw.repository;

import com.tecsup.lab03_daw.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}