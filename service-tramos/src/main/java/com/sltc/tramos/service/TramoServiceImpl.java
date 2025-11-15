package com.sltc.tramos.service;

import com.sltc.tramos.model.Tramo;
import com.sltc.tramos.repository.TramoRepository;
import com.sltc.tramos.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TramoServiceImpl implements TramoService {

    private final TramoRepository repository;

    @Override
    @Transactional
    public Tramo create(Tramo tramo) {
        return repository.save(tramo);
    }

    @Override
    @Transactional(readOnly = true)
    public Tramo getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Tramo no encontrado con id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tramo> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Tramo update(Long id, Tramo tramo) {
        Tramo existing = getById(id);
        tramo.setId(existing.getId());
        return repository.save(tramo);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Tramo no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }
}
