package com.sltc.solicitudes;

import com.sltc.solicitudes.Solicitud;
import com.sltc.solicitudes.SolicitudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {
    private final SolicitudRepository repo;

    public SolicitudController(SolicitudRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Solicitud> list() { return repo.findAll(); }

    @GetMapping("{id}")
    public ResponseEntity<Solicitud> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Solicitud s, UriComponentsBuilder ucb) {
        Solicitud created = repo.save(s);                      // <-- usar el objeto devuelto
        URI location = ucb.path("/api/solicitudes/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Solicitud s) {
        s.setId(id);
        int updated = repo.update(s);
        return updated > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        int deleted = repo.delete(id);
        return deleted > 0 ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    
    @GetMapping("{id}/estado")
    public ResponseEntity<java.util.Map<String, Object>> getEstado(@PathVariable Long id) {
        return repo.findById(id)
            .map(s -> {
                java.util.Map<String, Object> estado = new java.util.HashMap<>();
                estado.put("id", s.getId());
                estado.put("estado", s.getEstado());
                estado.put("tiempoEstimado", s.getTiempoEstimado());
                estado.put("tiempoReal", s.getTiempoReal());
                return ResponseEntity.ok(estado);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}