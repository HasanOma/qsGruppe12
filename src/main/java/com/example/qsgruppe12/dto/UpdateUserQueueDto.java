package com.example.qsgruppe12.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateUserQueueDto {

    @NotNull
    private String action;

    private int workNr;
}
