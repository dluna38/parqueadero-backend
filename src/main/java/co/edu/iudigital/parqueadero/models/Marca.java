package co.edu.iudigital.parqueadero.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "marca")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "marca_gen")
    @SequenceGenerator(name = "marca_gen",sequenceName = "marca_seq",allocationSize = 20)
    private Short id;
    @Column(nullable = false,length = 150)
    private String nombre;

    @PrePersist
    public void preInsert(){
        this.nombre = this.nombre.toUpperCase();
    }
}
