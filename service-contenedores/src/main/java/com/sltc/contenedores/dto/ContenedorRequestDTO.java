package com.sltc.contenedores.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContenedorRequestDTO {

    @NotBlank(message = "El número de contenedor es obligatorio")
    private String numeroContenedor;

    @NotBlank(message = "El tipo es obligatorio")
    private String tipo;

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser positivo")
    private Double peso;

    @NotNull(message = "El volumen es obligatorio")
    @Positive(message = "El volumen debe ser positivo")
    private Double volumen;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long idCliente;
}
