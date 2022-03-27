package com.example.qsgruppe12.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@SuperBuilder
@NoArgsConstructor
public class Student extends User{

//    @SequenceGenerator(
//            name = "student_sequence",
//            sequenceName = "user_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            generator = "student_sequence",
//            strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "student_id")
    private Long id;
    @NotBlank
    @Column(nullable = false)
    String firstName;
    @NotBlank
    @Column(nullable = false)
    String lastName;
    @Email
    @Column(unique = true,
            nullable = false)
    String email;
    @NotBlank
    @Column(nullable = false)
    String password;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    private List<StudentWorkApproved> workList;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    private List<Student_Course> courses;

}
