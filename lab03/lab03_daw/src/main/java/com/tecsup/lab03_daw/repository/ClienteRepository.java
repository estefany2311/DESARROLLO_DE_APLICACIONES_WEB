package com.tecsup.lab03_daw.repository;

import com.tecsup.lab03_daw.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}