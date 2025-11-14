package com.sltc.solicitudes.controller;

import com.sltc.solicitudes.model.Solicitud;
import com.sltc.solicitudes.service.SolicitudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {
    private final SolicitudService service;

    public SolicitudController(SolicitudService service) { this.service = service; }

    @GetMapping
    public List<Solicitud> all() { return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> get(@PathVariable Long id) {
        var s = service.findById(id);
        return s == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(s);
    }

    @PostMapping
    public Solicitud create(@RequestBody Solicitud s) { return service.save(s); }
}