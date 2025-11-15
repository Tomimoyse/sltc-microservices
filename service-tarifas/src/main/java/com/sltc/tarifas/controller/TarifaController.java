package com.sltc.tarifas.controller;

import com.sltc.tarifas.model.Tarifa;
import com.sltc.tarifas.service.TarifaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tarifas")
@RequiredArgsConstructor
@Tag(name = "Tarifas", description = "API para gestión de tarifas")
public class TarifaController {

    private final TarifaService service;

    @PostMapping
    @Operation(summary = "Crear un nuevo tarifa")
    public ResponseEntity<Tarifa> create(@Valid @RequestBody Tarifa tarifa) {
        return new ResponseEntity<>(service.create(tarifa), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener tarifa por ID")
    public ResponseEntity<Tarifa> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos los tarifas")
    public ResponseEntity<List<Tarifa>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tarifa")
    public ResponseEntity<Tarifa> update(@PathVariable Long id, @Valid @RequestBody Tarifa tarifa) {
        return ResponseEntity.ok(service.update(id, tarifa));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar tarifa")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
