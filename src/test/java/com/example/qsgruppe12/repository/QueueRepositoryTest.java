package com.example.qsgruppe12.repository;

import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.model.Queue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class QueueRepositoryTest {
    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        System.out.println("before each called");
        List<String> ruleList = new ArrayList<>(List.of("6_1_6"));
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

        Queue queue = Queue.builder()
                .id(1L)
                .course(course)
                .build();

        entityManager.persist(entityManager.merge(queue));
    }
    @AfterEach
    void teardown(){
        queueRepository.deleteAll();
    }

    @Test
    void getByCourseId() {
//        Queue queue = queueRepository.getByCourseId(1L).get();
//        assertEquals(1L, queue.getId());
    }

    @Test
    void getByCourseId_falseId(){
        assertTrue(queueRepository.getByCourseId(2L).isEmpty());
    }
}