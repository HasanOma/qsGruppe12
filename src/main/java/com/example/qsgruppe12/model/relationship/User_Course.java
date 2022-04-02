package com.example.qsgruppe12.model.relationship;

import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.model.Work;
import com.example.qsgruppe12.model.relationshipkey.UserCourseKey;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
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

    private String workApproved;

    @OneToMany
    private List<Work> workList;

    boolean canDoExam = false;

}