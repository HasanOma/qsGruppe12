package com.example.qsgruppe12.model;

import com.example.qsgruppe12.model.relationship.StudentWorkApproved;
import com.example.qsgruppe12.model.relationship.Student_Course;
import com.example.qsgruppe12.model.relationship.TA_Course;
import com.example.qsgruppe12.model.relationship.Teacher_Course;
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
    @Column(unique=true)
    private String name;
    private int totalWork;
    private int requiredWork;
    private String semester;
    private boolean archived;

    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    private List<Teacher_Course> teachers;

    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    private List<TA_Course> tas;


    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    private List<Student_Course> students;

    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    private List<StudentWorkApproved> studentWork;

}
