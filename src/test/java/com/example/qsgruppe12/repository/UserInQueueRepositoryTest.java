package com.example.qsgruppe12.repository;

import com.example.qsgruppe12.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserInQueueRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    List<String> ruleList = new ArrayList<>(List.of("6_1_6"));


    @BeforeEach
    void setUp() {
        Course course =
                Course.builder()
                        .id(1L)
                        .name("Fullstack applikasjonsutvikling")
                        .code("IDATT2105")
                        .semester("V22")
                        .queueActive(false)
                        .totalWork(6)
                        .rules(ruleList)
                        .nrOfStudents(100)
                        .build();
        entityManager.persist(entityManager.merge(course));
    }

    @Test
    void getByCourseId() {

    }
}