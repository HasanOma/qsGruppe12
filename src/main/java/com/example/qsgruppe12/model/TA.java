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
public class TA extends User{

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "user_sequence",
            strategy = GenerationType.SEQUENCE)
    @Column(name = "ta_id")
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
//    @JoinTable(name = "courses", joinColumns = @JoinColumn(name = "ta_id", referencedColumnName = "id" ),
//            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
//            uniqueConstraints = @UniqueConstraint(columnNames = {"ta_id", "course_id"}))
//    @ToString.Exclude
//    private List<Course> courses;

    @OneToMany(mappedBy = "ta")
    @ToString.Exclude
    private List<TA_Course> courses;

    public void setCourses(List<TA_Course> courses) {
        this.courses = courses;
    }

    public List<TA_Course> getCourses() {
        return courses;
    }
}
