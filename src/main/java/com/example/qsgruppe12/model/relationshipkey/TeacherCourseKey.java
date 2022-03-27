package com.example.qsgruppe12.model.relationshipkey;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TeacherCourseKey implements Serializable {

    @Column(name = "teacher_id")
    Long teacherId;

    @Column(name = "course_id")
    Long courseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TeacherCourseKey that = (TeacherCourseKey) o;
        return teacherId != null && Objects.equals(teacherId, that.teacherId)
                && courseId != null && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, courseId);
    }
}
