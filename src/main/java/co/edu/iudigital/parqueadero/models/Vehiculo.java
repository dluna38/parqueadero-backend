package co.edu.iudigital.parqueadero.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehiculo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "vehiculo_gen")
    @SequenceGenerator(name = "vehiculo_gen",sequenceName = "vehiculo_seq",allocationSize = 20)
    private Long id;
    @Column(nullable = false,unique = true,length = 15)
    private String placa;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_tipo_vehiculo",nullable = false,foreignKey = @ForeignKey(name = "FK_vehiculo_tipo"))
    private TipoVehiculo tipoVehiculo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_marca",nullable = false,foreignKey = @ForeignKey(name = "FK_vehiculo_marca"))
    private Marca marca;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_dueno",nullable = false,foreignKey = @ForeignKey(name = "FK_vehiculo_dueno"))
    private Dueno dueno;


    @PrePersist
    public void prePersistVehiculo(){
        this.placa = this.placa.toUpperCase();
    }
}
