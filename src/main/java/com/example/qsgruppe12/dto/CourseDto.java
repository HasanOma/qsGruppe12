package com.example.qsgruppe12.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {

    @NotNull
    private Long id;
    @NotNull
    @NotEmpty
    private String name;
    private int totalWork;
    private int requiredWork;
    private String semester;
    private boolean archived;
}
