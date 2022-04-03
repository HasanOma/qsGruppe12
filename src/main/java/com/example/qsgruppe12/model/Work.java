package com.example.qsgruppe12.model;

import com.example.qsgruppe12.model.relationship.User_Course;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Work {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private boolean completed;

    private Long courseId;

    private Long userId;

    @ManyToOne
    private User_Course user_course;
}
