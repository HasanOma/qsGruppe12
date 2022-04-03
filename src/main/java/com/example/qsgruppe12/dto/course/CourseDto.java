package com.example.qsgruppe12.dto.course;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "DTO sent back to user when creating or updating a course")
public class CourseDto {

    @ApiModelProperty(notes = "Id of the Course placement in the database")
    private Long id;
    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "Name of the course")
    private String name;
    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "Code of the course")
    private String code;
    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "The semester of the course")
    private String semester;
    @NotEmpty
    @NotNull
    @ApiModelProperty(notes = "Boolean to tell if the queue of the course is active")
    private boolean queueActive;
    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "Total work of the course")
    private int totalWork;
    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "Work nr approved")
    private String workApproved;
    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "Boolean to tell if the course is archived or not")
    private boolean archived;

    @ApiModelProperty(notes = "Rules of the course")
    private List<String> rules;
}