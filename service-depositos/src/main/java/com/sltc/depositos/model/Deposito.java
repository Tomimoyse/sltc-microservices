package com.sltc.depositos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "depositos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deposito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "La ubicación es obligatoria")
    @Column(nullable = false)
    private String ubicacion;

    @NotBlank(message = "El tipo es obligatorio")
    @Column(nullable = false)
    private String tipo;

    @NotNull(message = "La capacidad debe ser positiva")
    @Positive(message = "La capacidad debe ser positiva")
    @Column(nullable = false)
    private Double capacidad;

    @NotNull(message = "El costo debe ser positivo")
    @Positive(message = "El costo debe ser positivo")
    @Column(nullable = false)
    private Double costoAlmacenamiento;

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
