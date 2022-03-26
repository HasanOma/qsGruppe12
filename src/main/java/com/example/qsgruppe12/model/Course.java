package com.example.qsgruppe12.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
}
