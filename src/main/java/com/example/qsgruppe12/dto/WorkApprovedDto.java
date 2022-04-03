package com.example.qsgruppe12.dto;

import com.example.qsgruppe12.model.Work;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiModel(description = "DTO to return to user showing how many work are approved")
public class WorkApprovedDto {

    @ApiModelProperty(notes = "Course name and code")
    private String courseName;

    @ApiModelProperty(notes = "List of all works for the course")
    List<Work> work = new ArrayList<>();

    @ApiModelProperty(notes = "Boolean showing whether they are able to take the exam")
    private boolean examReady;

    @ApiModelProperty(notes = "Rules of the course")
    private String rules;
}