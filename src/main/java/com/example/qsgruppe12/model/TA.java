package com.example.qsgruppe12.model;

import com.example.qsgruppe12.model.relationship.TA_Course;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@SuperBuilder
@NoArgsConstructor
public class TA extends User{

    @Id
    @SequenceGenerator(
            name = "ta_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "ta_sequence",
            strategy = GenerationType.SEQUENCE)
    @Column(name = "ta_id")
    private Long id;



    @OneToMany(mappedBy = "ta")
    @ToString.Exclude
    private List<TA_Course> courses;

}
