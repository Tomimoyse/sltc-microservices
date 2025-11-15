package com.sltc.camiones.service;

import com.sltc.camiones.model.Camion;
import java.util.List;

public interface CamionService {
    Camion create(Camion camion);
    Camion getById(Long id);
    List<Camion> getAll();
    Camion update(Long id, Camion camion);
    void delete(Long id);
}
