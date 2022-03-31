package com.example.qsgruppe12.dto.userdtos;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserUpdateDto {

    @NotNull
    @NotEmpty
    private String firstName;
    @NotNull
    @NotEmpty
    private String lastName;
    @Email
    public String altEmail;

    @NotNull
    @NotEmpty
    private String password;
}
