package com.sltc.tarifas.repository;

import com.sltc.tarifas.model.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {
}
