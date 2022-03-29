package com.example.qsgruppe12.model;

import com.example.qsgruppe12.model.relationship.User_Course;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @Column(name = "course_id")
    private Long id;
    @NotNull
    @Column(unique=true)
    private String name;
    @NotNull
    @Column(unique=true)
    private String semester;
    @NotNull
    private int totalWork;
    @NotNull
    private String rules;
    @Builder.Default
    private boolean archived = false;

    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    private List<User_Course> users;

}
