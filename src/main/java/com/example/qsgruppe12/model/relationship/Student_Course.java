package com.example.qsgruppe12.model.relationship;

import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.model.Student;
import com.example.qsgruppe12.model.relationshipkey.StudentCourseKey;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Student_Course {

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

    String semester;

}
