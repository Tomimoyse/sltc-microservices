package com.sltc.tramos.controller;

import com.sltc.tramos.model.Tramo;
import com.sltc.tramos.service.TramoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tramos")
@RequiredArgsConstructor
@Tag(name = "Tramos", description = "API para gestión de tramos")
public class TramoController {

    private final TramoService service;

    @PostMapping
    @Operation(summary = "Crear un nuevo tramo")
    public ResponseEntity<Tramo> create(@Valid @RequestBody Tramo tramo) {
        return new ResponseEntity<>(service.create(tramo), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener tramo por ID")
    public ResponseEntity<Tramo> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos los tramos")
    public ResponseEntity<List<Tramo>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tramo")
    public ResponseEntity<Tramo> update(@PathVariable Long id, @Valid @RequestBody Tramo tramo) {
        return ResponseEntity.ok(service.update(id, tramo));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar tramo")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
