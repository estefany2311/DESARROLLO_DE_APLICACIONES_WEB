package com.tecsup.habitacion.repository;

import com.tecsup.habitacion.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    boolean existsByNumero(String numero);

    boolean existsByNumeroAndIdNot(String numero, Long id);
}
