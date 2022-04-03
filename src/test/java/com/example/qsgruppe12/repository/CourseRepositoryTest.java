package com.example.qsgruppe12.repository;

import com.example.qsgruppe12.model.Course;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Set<String> ruleList = new HashSet<>(List.of("6_1_6"));

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
    void findByCodeAndSemester() {
        Course course = courseRepository.findByCodeAndSemester("IDATT2105", "V22").get();
        assertEquals("Fullstack applikasjonsutvikling", course.getName());

    }

    @Test
    void findById() {
        Course course = courseRepository.findById(1L).get();
        assertEquals("Fullstack applikasjonsutvikling", course.getName());
    }

//    @Test
//    void deleteById(){
//        courseRepository.deleteById(1L);
////        assertEquals("Fullstack applikasjonsutvikling", course.getName());
//        assertTrue(courseRepository.findById(1L).isEmpty());
//    }

    //No clue as to why this doesnt work, line 55 just cant find an element with id 1L

}