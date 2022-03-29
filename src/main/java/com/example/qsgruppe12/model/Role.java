package com.example.qsgruppe12.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Role {

    @Id
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "role_sequence",
            strategy = GenerationType.SEQUENCE)
    @Column(name = "role_id")
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @OneToOne
    private User user;

    @PreRemove
    public void removeRelationships(){
        if(user!=null){
            user = null;
        }
    }
}
