package com.example.qsgruppe12.dto.userdtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGetInQueueDto {

    private Long userId;

    private String room;

    private String spot;

    private String workType;

    private String workNr;

    private String message;
}
