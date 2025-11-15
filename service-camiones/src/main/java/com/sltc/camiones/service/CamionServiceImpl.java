package com.sltc.camiones.service;

import com.sltc.camiones.model.Camion;
import com.sltc.camiones.repository.CamionRepository;
import com.sltc.camiones.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CamionServiceImpl implements CamionService {

    private final CamionRepository repository;

    @Override
    @Transactional
    public Camion create(Camion camion) {
        return repository.save(camion);
    }

    @Override
    @Transactional(readOnly = true)
    public Camion getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Camion no encontrado con id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Camion> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Camion update(Long id, Camion camion) {
        Camion existing = getById(id);
        camion.setId(existing.getId());
        return repository.save(camion);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Camion no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }
}
