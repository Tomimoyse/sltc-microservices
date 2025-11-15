package com.sltc.rutas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "rutas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El origen es obligatorio")
    @Column(nullable = false)
    private String origen;

    @NotBlank(message = "El destino es obligatorio")
    @Column(nullable = false)
    private String destino;

    @NotNull(message = "La distancia total debe ser positiva")
    @Positive(message = "La distancia total debe ser positiva")
    @Column(nullable = false)
    private Double distanciaTotal;

    @NotNull(message = "El costo total debe ser positivo")
    @Positive(message = "El costo total debe ser positivo")
    @Column(nullable = false)
    private Double costoTotal;

    @NotNull(message = "El ID de solicitud es obligatorio")
    @Column(nullable = false)
    private Long idSolicitud;

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
