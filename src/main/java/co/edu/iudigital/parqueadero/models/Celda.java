package co.edu.iudigital.parqueadero.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "celda")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Celda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "celda_gen")
    @SequenceGenerator(name = "celda_gen",sequenceName = "celda_seq",allocationSize = 20)
    Short id;
    @Column(length = 15,nullable = true,unique = true)
    String celda;
    @Column(columnDefinition = "boolean default false")
    boolean ocupada;


    @PrePersist
    public void prePersist(){
        this.celda = this.celda.toUpperCase();
    }
}
