package com.example.qsgruppe12.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Spot {

    @Id
    @GeneratedValue()
    private Long id;

    @NotNull
    private String name;

    @ManyToOne
    private Room room;

    @PreRemove
    public void preRemove(){
        if(room != null){
            room = null;
        }
    }
}
