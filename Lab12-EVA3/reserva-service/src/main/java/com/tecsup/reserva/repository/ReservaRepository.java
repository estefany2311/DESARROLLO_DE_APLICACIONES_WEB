package com.tecsup.reserva.repository;

import com.tecsup.reserva.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
