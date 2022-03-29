package com.example.qsgruppe12.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRegisterDto {

    @NotNull
    @NotEmpty
    private String name;

    private String semester;

    private int totalWork;

    private String rules;

    private boolean archived;
}
