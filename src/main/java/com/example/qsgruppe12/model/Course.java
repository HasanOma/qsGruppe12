package com.example.qsgruppe12.model;

import com.example.qsgruppe12.model.relationship.User_Course;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    @ElementCollection
    private List<String> rules;
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
