package com.sltc.solicitudes;

import com.sltc.solicitudes.Solicitud;
import com.sltc.solicitudes.SolicitudRepository;
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