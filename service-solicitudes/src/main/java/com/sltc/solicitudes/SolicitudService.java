package com.sltc.solicitudes.service;

import com.sltc.solicitudes.model.Solicitud;
import com.sltc.solicitudes.repository.SolicitudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudService {
    private final SolicitudRepository repo;

    public SolicitudService(SolicitudRepository repo) {
        this.repo = repo;
    }

    public Solicitud save(Solicitud s) { return repo.save(s); }
    public List<Solicitud> findAll() { return repo.findAll(); }
    public Solicitud findById(Long id) { return repo.findById(id).orElse(null); }
}