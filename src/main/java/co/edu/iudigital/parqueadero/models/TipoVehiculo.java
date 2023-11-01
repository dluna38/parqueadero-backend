package co.edu.iudigital.parqueadero.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipo_vehiculo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TipoVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "t_vehiculo_gen")
    @SequenceGenerator(name = "t_vehiculo_gen",sequenceName = "tvehiculo_seq",allocationSize = 20)
    private Short id;
    @Column(nullable = false,length = 100)
    private String tipo;

    @PrePersist
    public void preInsert(){
        this.tipo = this.tipo.toUpperCase();
    }
}
