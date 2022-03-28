package com.example.qsgruppe12.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @ManyToOne
    private Building building;

    @NotNull
    @OneToMany
    private List<Table> rooms;
}
