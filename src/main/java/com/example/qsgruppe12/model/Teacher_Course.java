package com.example.qsgruppe12.model;

import com.example.qsgruppe12.model.relationshipkey.TeacherCourseKey;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Teacher_Course {

    @EmbeddedId
    TeacherCourseKey teachCourseKey = new TeacherCourseKey();

    @ManyToOne
    @MapsId
    @JoinColumn(name = "teacher_id")
    Teacher teacher;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "course_id")
    Course course;

    String semester;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Teacher_Course that = (Teacher_Course) o;
        return teachCourseKey != null && Objects.equals(teachCourseKey, that.teachCourseKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teachCourseKey);
    }
}
