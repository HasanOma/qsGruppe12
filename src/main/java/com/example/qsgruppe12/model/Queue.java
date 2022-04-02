package com.example.qsgruppe12.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Queue {

    @Id
    @SequenceGenerator(
            name = "queue_sequence",
            sequenceName = "queue_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "queue_sequence",
            strategy = GenerationType.SEQUENCE)
    @Column(name = "queue_id")
    private Long id;

    private String message;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Course course;

    @OneToMany
    private List<UserInQueue> usersInQueue;
}
