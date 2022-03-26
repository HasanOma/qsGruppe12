package com.example.qsgruppe12.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Course {

    @Id
    private Long id;
    @Column(unique=true)
    private String name;
    private int totalWork;
    private int requiredWork;
    private String semester;
    private boolean archived;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "teachers", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id" ),
            inverseJoinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "teacher_id"}))
    private List<Teacher> teachers;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "teachingAssistants", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id" ),
            inverseJoinColumns = @JoinColumn(name = "ta_id", referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "ta_id"}))
    private List<TA> teachingAssistants;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "students", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id" ),
            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "student_id"}))
    private List<Student> students;
}
