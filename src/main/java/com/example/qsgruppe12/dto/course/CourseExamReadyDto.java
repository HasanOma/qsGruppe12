package com.example.qsgruppe12.dto.course;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "DTO for admin to see how many students qualify to take the exam")
public class CourseExamReadyDto {

    @ApiModelProperty(notes = "Id of the course")
    private Long id;

    @ApiModelProperty(notes = "Number of student that are qualified to take the exam")
    private int examReady;
}
