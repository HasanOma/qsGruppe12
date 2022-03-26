package com.example.qsgruppe12.model;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class Teacher extends User{

}
