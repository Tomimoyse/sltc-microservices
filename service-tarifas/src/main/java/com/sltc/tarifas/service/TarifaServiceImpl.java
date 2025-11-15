package com.sltc.tarifas.service;

import com.sltc.tarifas.model.Tarifa;
import com.sltc.tarifas.repository.TarifaRepository;
import com.sltc.tarifas.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarifaServiceImpl implements TarifaService {

    private final TarifaRepository repository;

    @Override
    @Transactional
    public Tarifa create(Tarifa tarifa) {
        return repository.save(tarifa);
    }

    @Override
    @Transactional(readOnly = true)
    public Tarifa getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Tarifa no encontrado con id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tarifa> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Tarifa update(Long id, Tarifa tarifa) {
        Tarifa existing = getById(id);
        tarifa.setId(existing.getId());
        return repository.save(tarifa);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Tarifa no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }
}
