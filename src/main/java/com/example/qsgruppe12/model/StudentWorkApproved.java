package com.example.qsgruppe12.model;

import com.example.qsgruppe12.model.relationshipkey.StudentCourseKey;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class StudentWorkApproved {

    @EmbeddedId
    StudentCourseKey studentCourseKey;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    Course course;

    int totalWork;
    int workDone;

}
