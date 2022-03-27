package com.example.qsgruppe12.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "teachers", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id" ),
//            inverseJoinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"),
//            uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "teacher_id"}))
//    @ToString.Exclude
//    private List<Teacher> teachers;

    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    private List<Teacher_Course> teachers;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "teachingAssistants", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id" ),
//            inverseJoinColumns = @JoinColumn(name = "ta_id", referencedColumnName = "id"),
//            uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "ta_id"}))
//    @ToString.Exclude
//    private List<TA> teachingAssistants;

    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    private List<TA_Course> tas;

//    @JoinTable(name = "students", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id" ),
//            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
//            uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "student_id"}))
//    @ManyToMany()
//    @ToString.Exclude
//    private List<Student> students;

    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    private List<Student_Course> students;

    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    private List<StudentWorkApproved> studentWork;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        Course course = (Course) o;
//        return id != null && Objects.equals(id, course.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}
