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
public class StudentWorkApproved {

    @EmbeddedId
    StudentCourseKey studentCourseKey = new StudentCourseKey();

    @ManyToOne
    @MapsId
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "course_id")
    Course course;

    int totalWork;
    int workDone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StudentWorkApproved that = (StudentWorkApproved) o;
        return studentCourseKey != null && Objects.equals(studentCourseKey, that.studentCourseKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentCourseKey);
    }
}
