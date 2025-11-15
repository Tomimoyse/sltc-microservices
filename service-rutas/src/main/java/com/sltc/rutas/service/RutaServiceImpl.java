package com.sltc.rutas.service;

import com.sltc.rutas.model.Ruta;
import com.sltc.rutas.repository.RutaRepository;
import com.sltc.rutas.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RutaServiceImpl implements RutaService {

    private final RutaRepository repository;

    @Override
    @Transactional
    public Ruta create(Ruta ruta) {
        return repository.save(ruta);
    }

    @Override
    @Transactional(readOnly = true)
    public Ruta getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Ruta no encontrado con id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ruta> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Ruta update(Long id, Ruta ruta) {
        Ruta existing = getById(id);
        ruta.setId(existing.getId());
        return repository.save(ruta);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Ruta no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }
}
