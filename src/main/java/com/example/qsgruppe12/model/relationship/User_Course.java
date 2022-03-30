package com.example.qsgruppe12.model.relationship;

import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.model.relationshipkey.UserCourseKey;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class User_Course {

    @EmbeddedId
    UserCourseKey userCourseKey;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    Course course;

    String workApproved;

    boolean canDoExam = false;

}