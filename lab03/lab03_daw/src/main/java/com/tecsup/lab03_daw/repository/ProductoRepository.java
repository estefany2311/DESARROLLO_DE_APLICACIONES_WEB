package com.tecsup.lab03_daw.repository;
import com.tecsup.lab03_daw.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
