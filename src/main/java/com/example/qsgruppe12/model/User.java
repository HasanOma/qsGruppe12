package com.example.qsgruppe12.model;

import com.example.qsgruppe12.model.relationship.User_Course;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "user_sequence",
            strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long id;
    @NotBlank
    @Column(nullable = false)
    public String firstName;
    @NotBlank
    @Column(nullable = false)
    public String lastName;
    @Email
    @Column(unique = true)
    public String email;
    @Email
    public String altEmail;
    @NotBlank
    @Column(nullable = false)
    public String password;

    private String userRoleName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<User_Course> courses;

    @PreRemove
    public void removeRelationships(){
        if(role!=null){
            role = null;
        }
        if (courses!=null){
            courses.clear();
        }
    }
}
