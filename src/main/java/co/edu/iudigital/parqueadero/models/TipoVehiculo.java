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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Short id;
    @Column(nullable = false,length = 100)
    private String tipo;

    @PrePersist
    public void preInsert(){
        this.tipo = this.tipo.toUpperCase();
    }
}
