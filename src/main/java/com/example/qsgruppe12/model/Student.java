package com.example.qsgruppe12.model;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@SuperBuilder
@NoArgsConstructor
public class Student extends User{

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "student_sequence",
            strategy = GenerationType.SEQUENCE)
    @Column(name = "student_id")
    private Long id;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    private List<StudentWorkApproved> workList;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    private List<Student_Course> courses;

}
