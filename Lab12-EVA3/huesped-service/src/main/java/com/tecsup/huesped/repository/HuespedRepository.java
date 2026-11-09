package com.tecsup.huesped.repository;

import com.tecsup.huesped.model.Huesped;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HuespedRepository extends JpaRepository<Huesped, Long> {

    boolean existsByDocumento(String documento);

    boolean existsByDocumentoAndIdNot(String documento, Long id);
}
