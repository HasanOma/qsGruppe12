package com.example.qsgruppe12.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class Teacher extends User{

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "user_sequence",
            strategy = GenerationType.SEQUENCE)
    @Column(name = "teacher_id")
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
//    @JoinTable(name = "courses", joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id" ),
//            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
//            uniqueConstraints = @UniqueConstraint(columnNames = {"teacher_id", "course_id"}))
//    @ToString.Exclude
//    private List<Course> courses;

    @OneToMany(mappedBy = "teacher")
    @ToString.Exclude
    private List<Teacher_Course> courses;

    public void setCourses(List<Teacher_Course> courses) {
        this.courses = courses;
    }

    public List<Teacher_Course> getCourses() {
        return courses;
    }
}
