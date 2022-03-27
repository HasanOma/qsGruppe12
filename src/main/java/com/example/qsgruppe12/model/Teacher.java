package com.example.qsgruppe12.model;

import com.example.qsgruppe12.model.relationship.Teacher_Course;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Entity
@SuperBuilder
@NoArgsConstructor
public class Teacher extends User{

    @Id
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "teacher_sequence",
            strategy = GenerationType.SEQUENCE)
    @Column(name = "teacher_id")
    private Long id;

    @OneToMany(mappedBy = "teacher")
    @ToString.Exclude
    private Set<Teacher_Course> courses;

}
