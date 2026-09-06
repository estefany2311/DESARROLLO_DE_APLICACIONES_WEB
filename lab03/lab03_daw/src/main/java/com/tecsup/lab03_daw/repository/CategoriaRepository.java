package com.tecsup.lab03_daw.repository;

import com.tecsup.lab03_daw.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}