package com.example.qsgruppe12.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class User {

//    @NotBlank
//    @Column(nullable = false)
//    String firstName;
//    @NotBlank
//    @Column(nullable = false)
//    String lastName;
//    @Email
//    @Column(unique = true,
//            nullable = false)
//    String email;
//    @NotBlank
//    @Column(nullable = false)
//    String password;
}
