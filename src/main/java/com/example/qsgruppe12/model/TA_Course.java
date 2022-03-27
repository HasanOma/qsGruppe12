package com.example.qsgruppe12.model;

import com.example.qsgruppe12.model.relationshipkey.TACourseKey;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class TA_Course {

    @EmbeddedId
    TACourseKey taCourseKey = new TACourseKey();

    @ManyToOne
    @MapsId
    @JoinColumn(name = "ta_id")
    TA ta;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "course_id")
    Course course;

    String semester;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TA_Course ta_course = (TA_Course) o;
        return taCourseKey != null && Objects.equals(taCourseKey, ta_course.taCourseKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taCourseKey);
    }
}
