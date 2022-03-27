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
import java.util.Set;

@Entity
@SuperBuilder
@NoArgsConstructor
public class Teacher{

//    @SequenceGenerator(
//            name = "teacher_sequence",
//            sequenceName = "user_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            generator = "teacher_sequence",
//            strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "teacher_id")
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

    @OneToMany(mappedBy = "teacher")
    @ToString.Exclude
    private Set<Teacher_Course> courses;

}
