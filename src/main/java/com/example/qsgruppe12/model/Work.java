package com.example.qsgruppe12.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Work {

    @Id
    @SequenceGenerator(
            name = "work_sequence",
            sequenceName = "work_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "work_sequence",
            strategy = GenerationType.SEQUENCE)
    @Column(name = "work_id")
    private Long id;

    private int Nr;

    private boolean completed;
}
