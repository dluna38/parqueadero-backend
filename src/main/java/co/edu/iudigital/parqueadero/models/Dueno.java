package co.edu.iudigital.parqueadero.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dueno")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Dueno {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "dueno_gen")
    @SequenceGenerator(name = "dueno_gen",sequenceName = "dueno_seq",allocationSize = 20)
    private Long id;
    @Column(nullable = false,length = 150)
    private String nombre;
    @Column(name = "documento_identificacion", nullable = false, unique = true,length = 15)
    private String documentoIdentificacion;
    @Column(nullable = false,length = 10)
    private String telefono;
    @Column(length = 100)
    private String correo;

    @PrePersist
    public void prePersistVehiculo(){
        this.documentoIdentificacion = this.documentoIdentificacion.toUpperCase();
    }
}
