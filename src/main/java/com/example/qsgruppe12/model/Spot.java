package com.example.qsgruppe12.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * A class representing a spot in a {@link Room} where a student might sit
 */
@Entity
public class Spot {

    @Id
    @SequenceGenerator(
            name = "room_sequence",
            sequenceName = "room_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "room_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne
    private Room room;

    @PreRemove
    public void preRemove(){
        if(room != null){
            room = null;
        }
    }
}
