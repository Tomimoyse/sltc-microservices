package com.sltc.depositos.service;

import com.sltc.depositos.model.Deposito;
import com.sltc.depositos.repository.DepositoRepository;
import com.sltc.depositos.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepositoServiceImpl implements DepositoService {

    private final DepositoRepository repository;

    @Override
    @Transactional
    public Deposito create(Deposito deposito) {
        return repository.save(deposito);
    }

    @Override
    @Transactional(readOnly = true)
    public Deposito getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Deposito no encontrado con id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Deposito> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Deposito update(Long id, Deposito deposito) {
        Deposito existing = getById(id);
        deposito.setId(existing.getId());
        return repository.save(deposito);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Deposito no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }
}
