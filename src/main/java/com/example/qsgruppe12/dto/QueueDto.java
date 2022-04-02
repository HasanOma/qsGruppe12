package com.example.qsgruppe12.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@ApiModel(description = "Queue DTO sent to show the queue")
public class QueueDto {

    @ApiModelProperty(notes = "Id of the student that wants to get in queue")
    private Long userId;

    @ApiModelProperty(notes = "Full name of the student")
    private String fullName;

    @ApiModelProperty(notes = "Room where the guidance is given")
    private String room;

    @ApiModelProperty(notes = "Spot in the room that the student sits in")
    private String spot;

    @ApiModelProperty(notes = "Work type to specify if it is guidance or approving the student accuires")
    private String workType;

    @ApiModelProperty(notes = "The nr of the work the student is currently at")
    private String workNr;

    @ApiModelProperty(notes = "Time when the student got in the queue")
    private String localDate;

    @ApiModelProperty(notes = "Message from the student to the TA or Teacher")
    private String message;
}
