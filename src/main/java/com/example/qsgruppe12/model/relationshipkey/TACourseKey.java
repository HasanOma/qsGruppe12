package com.example.qsgruppe12.model.relationshipkey;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TACourseKey implements Serializable {

    @Column(name = "ta_id")
    Long taId;

    @Column(name = "course_id")
    Long courseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TACourseKey that = (TACourseKey) o;
        return taId != null && Objects.equals(taId, that.taId)
                && courseId != null && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taId, courseId);
    }
}
