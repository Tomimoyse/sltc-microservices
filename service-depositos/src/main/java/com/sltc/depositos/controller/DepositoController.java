package com.sltc.depositos.controller;

import com.sltc.depositos.model.Deposito;
import com.sltc.depositos.service.DepositoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/depositos")
@RequiredArgsConstructor
@Tag(name = "Depositos", description = "API para gestión de depositos")
public class DepositoController {

    private final DepositoService service;

    @PostMapping
    @Operation(summary = "Crear un nuevo deposito")
    public ResponseEntity<Deposito> create(@Valid @RequestBody Deposito deposito) {
        return new ResponseEntity<>(service.create(deposito), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener deposito por ID")
    public ResponseEntity<Deposito> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos los depositos")
    public ResponseEntity<List<Deposito>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar deposito")
    public ResponseEntity<Deposito> update(@PathVariable Long id, @Valid @RequestBody Deposito deposito) {
        return ResponseEntity.ok(service.update(id, deposito));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar deposito")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
