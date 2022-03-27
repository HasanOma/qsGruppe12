package com.example.qsgruppe12.model;

import com.example.qsgruppe12.model.relationshipkey.TACourseKey;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class TA_Course {

    @EmbeddedId
    TACourseKey taCourseKey;

    @ManyToOne
    @MapsId("taId")
    @JoinColumn(name = "ta_id")
    TA ta;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    Course course;

    String semester;

}
