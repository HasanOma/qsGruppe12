package com.example.qsgruppe12.dto.userdtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEmailsDto {

    @NotNull
    @NotBlank
    @Email
    private String Email;
}
