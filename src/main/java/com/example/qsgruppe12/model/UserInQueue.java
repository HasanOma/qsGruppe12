package com.example.qsgruppe12.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalTime;

/**
 * A class representing a {@link User} in a {@link Queue}
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserInQueue {

    @Id
    @SequenceGenerator(
            name = "user_in_queue_sequence",
            sequenceName = "user_in_queue_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "user_in_queue_sequence",
            strategy = GenerationType.SEQUENCE)
    @Column(name = "user_in_queue_id")
    private Long id;

    private Long courseId;

    private String fullName;

    private String room;

    private String spot;

    private String workType;

    private String workNr;

    private LocalTime localDate;

    private boolean helped;

    private String message;

    @ManyToOne
    private Queue queue;

    @PreRemove
    void remove(){
        if(queue!=null){
            queue = null;
        }
    }
}
