package com.sltc.tramos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tramos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tramo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El origen es obligatorio")
    @Column(nullable = false)
    private String origen;

    @NotBlank(message = "El destino es obligatorio")
    @Column(nullable = false)
    private String destino;

    @NotNull(message = "La distancia debe ser positiva")
    @Positive(message = "La distancia debe ser positiva")
    @Column(nullable = false)
    private Double distancia;

    @NotNull(message = "El costo debe ser positivo")
    @Positive(message = "El costo debe ser positivo")
    @Column(nullable = false)
    private Double costo;

    @NotBlank(message = "El estado es obligatorio")
    @Column(nullable = false)
    private String estado;

    @NotNull(message = "El ID de ruta es obligatorio")
    @Column(nullable = false)
    private Long idRuta;

    @Column(nullable = false)
    private String dominoCamion;

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
