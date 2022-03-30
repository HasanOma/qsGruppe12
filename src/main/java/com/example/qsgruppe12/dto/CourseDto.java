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
    @NotEmpty
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
    @NotNull
    @NotEmpty
    private String workApproved;
    @NotNull
    @NotEmpty
    private boolean archived;

    private String rules;
}