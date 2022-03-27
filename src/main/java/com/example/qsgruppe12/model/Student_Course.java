package com.example.qsgruppe12.model;

import com.example.qsgruppe12.model.relationshipkey.StudentCourseKey;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Student_Course {

    @EmbeddedId
    StudentCourseKey studentCourseKey = new StudentCourseKey();

//    @EmbeddedId
    public StudentCourseKey getCourse() {
        return studentCourseKey;
    }

    @ManyToOne
    @MapsId
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "course_id")
    Course course;

    String semester;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Student_Course that = (Student_Course) o;
        return studentCourseKey != null && Objects.equals(studentCourseKey, that.studentCourseKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentCourseKey);
    }
}
