package com.example.qsgruppe12.model;

import com.example.qsgruppe12.model.relationship.User_Course;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * A class that represents a course
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "course_sequence",
            strategy = GenerationType.SEQUENCE)
    @Column(name = "course_id")
    private Long id;
    @NotNull
    private String name;
    @NotNull
    @Column(unique=true)
    private String code;
    @NotNull
    private String semester;
    @NotNull
    private boolean queueActive;
    @NotNull
    private int totalWork;
    @NotNull
    private String rules;
    @Builder.Default
    private boolean archived = false;
    @NotNull
    private int nrOfStudents;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<User_Course> users;

    @OneToOne
    @JoinColumn(name = "queue_id", referencedColumnName = "queue_id")
    private Queue queue;

    public int getNrOfStudents() {
        return nrOfStudents;
    }
}
