package com.example.qsgruppe12.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Course course;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<User> usersInQueue;
}
