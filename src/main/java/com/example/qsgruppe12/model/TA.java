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
public class TA{

//    @SequenceGenerator(
//            name = "ta_sequence",
//            sequenceName = "user_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            generator = "ta_sequence",
//            strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "ta_id")
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


    @OneToMany(mappedBy = "ta")
    @ToString.Exclude
    private Set<TA_Course> courses;

}
