package com.example.qsgruppe12.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * A class representing a room where a student might sit
 */
@Entity
public class Room {

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

    @NotNull
    private String picture;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Spot> spot;

}
