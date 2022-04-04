package com.example.qsgruppe12.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFileRegistration {



    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    @Email
    private String email;

}
