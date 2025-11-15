package com.sltc.tarifas.service;

import com.sltc.tarifas.model.Tarifa;
import java.util.List;

public interface TarifaService {
    Tarifa create(Tarifa tarifa);
    Tarifa getById(Long id);
    List<Tarifa> getAll();
    Tarifa update(Long id, Tarifa tarifa);
    void delete(Long id);
}
