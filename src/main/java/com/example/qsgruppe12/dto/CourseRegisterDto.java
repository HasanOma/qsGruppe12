package com.example.qsgruppe12.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRegisterDto {

    private String name;
    @NotNull
    @NotEmpty
    private String code;
    @NotNull
    @NotEmpty
    private String semester;
    @NotNull
    @NotEmpty
    private int totalWork;

    private String rules;
}
