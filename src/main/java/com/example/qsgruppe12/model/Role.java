package com.example.qsgruppe12.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
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
    private List<Teacher_Course> courses;

}
