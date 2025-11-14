package main.java.com.sltc.solicitudes;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "solicitud")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double costoEstimado;
    private Double costoFinal;
    private Integer tiempoEstimado;
    private Integer tiempoReal;
    private String estado;

    private Long idCliente;
    private Long idContenedor;
}