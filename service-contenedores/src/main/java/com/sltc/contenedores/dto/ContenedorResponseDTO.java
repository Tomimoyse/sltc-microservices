package com.sltc.contenedores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContenedorResponseDTO {

    private Long id;
    private String numeroContenedor;
    private String tipo;
    private Double peso;
    private Double volumen;
    private String estado;
    private Long idCliente;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
