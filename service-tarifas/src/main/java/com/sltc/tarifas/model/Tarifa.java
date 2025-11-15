package com.sltc.tarifas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tarifas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El tipo es obligatorio")
    @Column(nullable = false)
    private String tipo;

    @NotNull(message = "La tarifa base debe ser positiva")
    @Positive(message = "La tarifa base debe ser positiva")
    @Column(nullable = false)
    private Double tarifaBase;

    @NotNull(message = "La tarifa por km debe ser positiva")
    @Positive(message = "La tarifa por km debe ser positiva")
    @Column(nullable = false)
    private Double tarifaPorKm;

    @NotNull(message = "El valor de combustible debe ser positivo")
    @Positive(message = "El valor de combustible debe ser positivo")
    @Column(nullable = false)
    private Double valorCombustible;

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
