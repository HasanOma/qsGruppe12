package com.example.qsgruppe12.model;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class TA extends User{

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "courses", joinColumns = @JoinColumn(name = "ta_id", referencedColumnName = "id" ),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"ta_id", "course_id"}))
    private List<Course> courses;
}
