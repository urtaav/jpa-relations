package com.urtaav.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private Integer age;
    private String nationality;

    /*
     -ManyToOne:un jugador pertenece a un equipo
     -targetEntity: le digo con que clase se va hacer la relación
     */
    @ManyToOne(targetEntity = Club.class)
    @JoinColumn(name = "id_club")
    private Club club;

}
