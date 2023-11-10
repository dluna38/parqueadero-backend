package co.edu.iudigital.parqueadero.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pago_gen")
    @SequenceGenerator(name = "pago_gen",sequenceName = "pago_seq",allocationSize = 20)
    Long id;
    Long cobroTotal;
    @OneToOne
    @JoinColumn(name = "fk_estancia",foreignKey = @ForeignKey(name = "fk_estancia_pago"))
    Estancia estancia;
    @ManyToOne
    @JoinColumn(name = "fk_tarifa",foreignKey = @ForeignKey(name = "fk_tarifa_pago"))
    Tarifa tarifa;

    LocalDateTime fechaGeneracion;

    @PrePersist
    public void prePersist(){
        this.fechaGeneracion=LocalDateTime.now();
    }
}
