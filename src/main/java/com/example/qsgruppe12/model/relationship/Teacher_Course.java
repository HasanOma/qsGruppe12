package com.example.qsgruppe12.model.relationship;

import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.model.Teacher;
import com.example.qsgruppe12.model.relationshipkey.TeacherCourseKey;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Teacher_Course {

    @EmbeddedId
    TeacherCourseKey teachCourseKey;

    @ManyToOne
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    Teacher teacher;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    Course course;

    String semester;

}
