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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Short id;
    @Column(nullable = false,length = 150)
    private String nombre;

    @PrePersist
    public void preInsert(){
        this.nombre = this.nombre.toUpperCase();
    }
}
