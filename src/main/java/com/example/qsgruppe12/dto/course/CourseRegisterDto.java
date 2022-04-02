package com.example.qsgruppe12.dto.course;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRegisterDto {

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
    private int totalWork;

    private List<String> rules;
}
