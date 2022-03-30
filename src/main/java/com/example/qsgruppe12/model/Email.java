package com.example.qsgruppe12.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Email {

    @NotNull
    private String from;

    @NotNull
    private String to;

    private String subject;

    @NotNull
    private String message;
}
