package com.example.qsgruppe12.model.relationshipkey;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserCourseKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "course_id")
    Long courseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserCourseKey that = (UserCourseKey) o;
        return userId != null && Objects.equals(userId, that.userId)
                && courseId != null && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, courseId);
    }
}
