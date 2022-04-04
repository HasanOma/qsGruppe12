package com.example.qsgruppe12.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Variable class for sending admin data to select from")
public class StudentCourseDto {

    @ApiModelProperty(notes = "List of all course IDs")
    public List<Long> courseIds;

    @ApiModelProperty(notes = "List of all courses name and")
    public List<String> course;

    @ApiModelProperty(notes = "List of all user emails")
    public List<String> email;
}
