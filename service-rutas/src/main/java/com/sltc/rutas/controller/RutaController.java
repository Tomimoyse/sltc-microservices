package com.sltc.rutas.controller;

import com.sltc.rutas.model.Ruta;
import com.sltc.rutas.service.RutaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rutas")
@RequiredArgsConstructor
@Tag(name = "Rutas", description = "API para gestión de rutas")
public class RutaController {

    private final RutaService service;

    @PostMapping
    @Operation(summary = "Crear un nuevo ruta")
    public ResponseEntity<Ruta> create(@Valid @RequestBody Ruta ruta) {
        return new ResponseEntity<>(service.create(ruta), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener ruta por ID")
    public ResponseEntity<Ruta> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos los rutas")
    public ResponseEntity<List<Ruta>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar ruta")
    public ResponseEntity<Ruta> update(@PathVariable Long id, @Valid @RequestBody Ruta ruta) {
        return ResponseEntity.ok(service.update(id, ruta));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar ruta")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
