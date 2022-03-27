package com.example.qsgruppe12.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class Student extends User{

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "user_sequence",
            strategy = GenerationType.SEQUENCE)
    @Column(name = "student_id")
    private Long id;
    @NotBlank
    @Column(nullable = false)
    String firstName;
    @NotBlank
    @Column(nullable = false)
    String lastName;
    @Email
    @Column(unique = true,
            nullable = false)
    String email;
    @NotBlank
    @Column(nullable = false)
    String password;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "courses", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id" ),
//            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
//            uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "course_id"}))
//    @ToString.Exclude
//    private List<Course> courses;
//
//    public void setCourses(List<Course> courses) {
//        this.courses = courses;
//    }
//
//    public List<Course> getCourses() {
//        return courses;
//    }

        @OneToMany(mappedBy = "student")
    @ToString.Exclude
    private List<StudentWorkApproved> workList;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    private List<Student_Course> courses;

    public void setWorkList(List<StudentWorkApproved> workList) {
        this.workList = workList;
    }

    public List<StudentWorkApproved> getWorkList() {
        return workList;
    }

    public void setCourses(List<Student_Course> courses) {
        this.courses = courses;
    }

    public List<Student_Course> getCourses() {
        return courses;
    }
}
