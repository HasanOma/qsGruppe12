package com.example.qsgruppe12.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class QueueDto {

    private Long courseId;

    private String fullName;

    private String room;

    private String spot;

    private String workType;

    private String workNr;

    private LocalDate localDate;

    private String message;
}
