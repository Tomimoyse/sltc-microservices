package com.sltc.camiones.repository;

import com.sltc.camiones.model.Camion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CamionRepository extends JpaRepository<Camion, Long> {
}
