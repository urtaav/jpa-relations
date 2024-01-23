package com.urtaav.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    /*
    -OneToOne:relación 1 a 1
    -targetEntity: le digo con que clase se va hacer la relación
    -cascade: comportamiento en cascada si persito un equipo de futbol se agrega,actualiza o elimina el coach
    -para este caso al agregar un club se inserta el coach y si se llegase a eliminar el coach no se va tocar*/
    @OneToOne(targetEntity = Coach.class,cascade = CascadeType.PERSIST)
    //JoinColumn: indica el nombre con el que se crear la llave foranea
    @JoinColumn(name = "id_coach")
    private Coach coach;

    /*
    -OneToMany:Realcion 1 a muchos es decir un equipo puede tener muchos jugadores
    -targetEntity: le digo con que clase se va hacer la relación
    -fetch:indica si al hacer la consulta se traera los players para este caso Lazy le indica que no hasta que se haga la petición de ellos
    -mappedBy:para referenciar la propiedad de la entidad propietaria que gestiona la relación.
    -*/
    @OneToMany(targetEntity = Player.class,fetch = FetchType.LAZY,mappedBy = "club")
    private List<Player> players;
    /*
    *
    * */
    @ManyToOne(targetEntity = FootballAssociation.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private FootballAssociation footballAssociation;
    /*
    * ManyToMany:Un equiponjuega una o muchas competiciones
    * JoinTable:permite personalizar la tabla intermedia que se va crear
    * name: nombre de la tabla intermedia creada
    * joinColumns: modifica el nombre de la tabla intermedia en este caso se llamara club
    * inverseJoinColumns:modifica el nombre en la tabla intermedia
    * */
    @ManyToMany(targetEntity = FootballCompetition.class, fetch = FetchType.LAZY)
    @JoinTable(name = "club_competition", joinColumns = @JoinColumn(name = "club"), inverseJoinColumns = @JoinColumn(name = "competition"))
    private List<FootballCompetition> footballCompetitions;
}
