package com.sltc.rutas.service;

import com.sltc.rutas.model.Ruta;
import java.util.List;

public interface RutaService {
    Ruta create(Ruta ruta);
    Ruta getById(Long id);
    List<Ruta> getAll();
    Ruta update(Long id, Ruta ruta);
    void delete(Long id);
}
