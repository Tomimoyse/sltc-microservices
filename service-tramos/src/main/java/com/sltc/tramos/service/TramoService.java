package com.sltc.tramos.service;

import com.sltc.tramos.model.Tramo;
import java.util.List;

public interface TramoService {
    Tramo create(Tramo tramo);
    Tramo getById(Long id);
    List<Tramo> getAll();
    Tramo update(Long id, Tramo tramo);
    void delete(Long id);
}
