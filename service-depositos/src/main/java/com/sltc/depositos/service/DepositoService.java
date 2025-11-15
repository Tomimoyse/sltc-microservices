package com.sltc.depositos.service;

import com.sltc.depositos.model.Deposito;
import java.util.List;

public interface DepositoService {
    Deposito create(Deposito deposito);
    Deposito getById(Long id);
    List<Deposito> getAll();
    Deposito update(Long id, Deposito deposito);
    void delete(Long id);
}
