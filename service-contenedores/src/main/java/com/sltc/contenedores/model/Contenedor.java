package com.sltc.contenedores.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "contenedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contenedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El número de contenedor es obligatorio")
    @Column(nullable = false, unique = true)
    private String numeroContenedor;

    @NotBlank(message = "El tipo es obligatorio")
    @Column(nullable = false)
    private String tipo;

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser positivo")
    @Column(nullable = false)
    private Double peso;

    @NotNull(message = "El volumen es obligatorio")
    @Positive(message = "El volumen debe ser positivo")
    @Column(nullable = false)
    private Double volumen;

    @NotBlank(message = "El estado es obligatorio")
    @Column(nullable = false)
    private String estado;

    @NotNull(message = "El ID del cliente es obligatorio")
    @Column(nullable = false)
    private Long idCliente;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
