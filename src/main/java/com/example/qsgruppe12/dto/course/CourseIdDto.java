package com.example.qsgruppe12.dto.course;

import lombok.Data;

@Data
public class CourseIdDto {

    public Long id;

    public String name;

    public String code;

    public boolean archived;

    public String rules;

    public int examReady;

    public int nrOfStudents;
}
