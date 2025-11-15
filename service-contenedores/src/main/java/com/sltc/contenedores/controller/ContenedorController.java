package com.sltc.contenedores.controller;

import com.sltc.contenedores.dto.ContenedorRequestDTO;
import com.sltc.contenedores.dto.ContenedorResponseDTO;
import com.sltc.contenedores.service.ContenedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contenedores")
@RequiredArgsConstructor
@Tag(name = "Contenedores", description = "API para gestión de contenedores")
public class ContenedorController {

    private final ContenedorService contenedorService;

    @PostMapping
    @Operation(summary = "Registrar un nuevo contenedor")
    public ResponseEntity<ContenedorResponseDTO> createContenedor(@Valid @RequestBody ContenedorRequestDTO requestDTO) {
        ContenedorResponseDTO responseDTO = contenedorService.createContenedor(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un contenedor por ID")
    public ResponseEntity<ContenedorResponseDTO> getContenedorById(@PathVariable Long id) {
        ContenedorResponseDTO responseDTO = contenedorService.getContenedorById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    @Operation(summary = "Listar todos los contenedores")
    public ResponseEntity<List<ContenedorResponseDTO>> getAllContenedores() {
        List<ContenedorResponseDTO> contenedores = contenedorService.getAllContenedores();
        return ResponseEntity.ok(contenedores);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un contenedor")
    public ResponseEntity<ContenedorResponseDTO> updateContenedor(
            @PathVariable Long id,
            @Valid @RequestBody ContenedorRequestDTO requestDTO) {
        ContenedorResponseDTO responseDTO = contenedorService.updateContenedor(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un contenedor")
    public ResponseEntity<Void> deleteContenedor(@PathVariable Long id) {
        contenedorService.deleteContenedor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{idCliente}")
    @Operation(summary = "Filtrar contenedores por cliente")
    public ResponseEntity<List<ContenedorResponseDTO>> getContenedoresByCliente(@PathVariable Long idCliente) {
        List<ContenedorResponseDTO> contenedores = contenedorService.getContenedoresByCliente(idCliente);
        return ResponseEntity.ok(contenedores);
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Consultar estado de contenedores")
    public ResponseEntity<List<ContenedorResponseDTO>> getContenedoresByEstado(@PathVariable String estado) {
        List<ContenedorResponseDTO> contenedores = contenedorService.getContenedoresByEstado(estado);
        return ResponseEntity.ok(contenedores);
    }
}
