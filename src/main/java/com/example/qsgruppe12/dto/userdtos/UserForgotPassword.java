package com.example.qsgruppe12.dto.userdtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserForgotPassword {

    @Email
    @NotNull
    private String email;
}
