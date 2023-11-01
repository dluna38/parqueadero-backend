package co.edu.iudigital.parqueadero.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "estancia")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Estancia {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "estancia_gen")
    @SequenceGenerator(name = "estancia_gen",sequenceName = "estancia_seq",allocationSize = 20)
    private Long id;
    @Column(name = "fecha_entrada",nullable = false)
    private LocalDateTime fechaEntrada;
    @Column(name = "fecha_salida")
    private LocalDateTime fechaSalida;
    @Column(name = "minutos_totales")
    private Long minutosTotales;
    @ManyToOne
    @JoinColumn(name = "fk_vehiculo", nullable = false,foreignKey = @ForeignKey(name = "FK_movimiento_vehiculo"))
    private Vehiculo vehiculo;

    @PrePersist
    public void prePersistEstancia(){
        this.fechaEntrada = LocalDateTime.now();
    }


}
