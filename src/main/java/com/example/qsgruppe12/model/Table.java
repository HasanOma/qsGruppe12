package com.example.qsgruppe12.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Table {

    @Id
    @SequenceGenerator(
            name = "table_sequence",
            sequenceName = "table_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "table_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne
    private Table table;
}
