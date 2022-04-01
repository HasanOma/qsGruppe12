package com.example.qsgruppe12.dto.course;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseExamReadyDto {

    private Long id;

    private int examReady;
}
