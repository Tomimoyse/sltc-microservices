package com.sltc.camiones.controller;

import com.sltc.camiones.model.Camion;
import com.sltc.camiones.service.CamionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/camiones")
@RequiredArgsConstructor
@Tag(name = "Camions", description = "API para gestión de camiones")
public class CamionController {

    private final CamionService service;

    @PostMapping
    @Operation(summary = "Crear un nuevo camion")
    public ResponseEntity<Camion> create(@Valid @RequestBody Camion camion) {
        return new ResponseEntity<>(service.create(camion), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener camion por ID")
    public ResponseEntity<Camion> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos los camiones")
    public ResponseEntity<List<Camion>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar camion")
    public ResponseEntity<Camion> update(@PathVariable Long id, @Valid @RequestBody Camion camion) {
        return ResponseEntity.ok(service.update(id, camion));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar camion")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/dominio/{dominio}")
    @Operation(summary = "Obtener camión por dominio")
    public ResponseEntity<Camion> getByDominio(@PathVariable String dominio) {
        return ResponseEntity.ok(service.getAll().stream()
            .filter(c -> c.getDominio().equals(dominio))
            .findFirst()
            .orElseThrow(() -> new com.sltc.camiones.exception.ResourceNotFoundException("Camión no encontrado con dominio: " + dominio)));
    }
    
    @GetMapping("/disponibles")
    @Operation(summary = "Listar camiones disponibles")
    public ResponseEntity<java.util.List<Camion>> getDisponibles() {
        return ResponseEntity.ok(service.getAll().stream()
            .filter(Camion::getDisponible)
            .collect(java.util.stream.Collectors.toList()));
    }
}
