package co.edu.iudigital.parqueadero.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "tarifa_gen")
    @SequenceGenerator(name = "tarifa_gen",sequenceName = "tarifa_seq",allocationSize = 20)
    Integer id;
    @Column(nullable = false)
    Long tarifaValor;
    @Column(nullable = false)
    Long porMinutos;
    @Column(nullable = false)
    LocalDateTime fechaImplementacion;

    @PrePersist
    public void prePersist(){
        this.fechaImplementacion=LocalDateTime.now();
    }
}
