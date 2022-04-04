package com.example.qsgruppe12.repository;

import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.model.relationship.User_Course;
import com.example.qsgruppe12.model.relationshipkey.UserCourseKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//class User_CourseRepositoryTest {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    CourseRepository courseRepository;
//
//    @Autowired
//    private User_CourseRepository userCourseRepository;
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @BeforeEach
////    void setUp() {
////        Course course =
////                Course.builder()
////                        .id(1L)
////                        .name("Fullstack applikasjonsutvikling")
////                        .code("IDATT2105")
////                        .semester("V22")
////                        .queueActive(false)
////                        .totalWork(6)
////                        .rules("6_1_6")
////                        .nrOfStudents(100)
////                        .build();
////        entityManager.persist(entityManager.merge(course));
////        User user = User.builder()
////                .id(1L)
////                .firstName("Ola")
////                .lastName("Normann")
////                .email("email@emailsen.no")
////                .password("hei")
////                .build();
////        entityManager.persist(entityManager.merge(user));
////        UserCourseKey userCourseKey = new UserCourseKey();
////        userCourseKey.setUserId(1L);
////        userCourseKey.setCourseId(1L);
////        User_Course userCourse = User_Course.builder()
////                .userCourseKey(userCourseKey)
////                .course(course)
////                .user(user)
////                .build();
////        entityManager.persist(entityManager.merge(userCourse));
////    }
//
//    @Test
//    void findAllByUserId() {
////        List<User> userCourse = userRepository.findAllById((userCourseRepository.findAllByUserId(1L)));
////        assertEquals(userCourse.get(0), 1L);
//    }
//
//    @Test
//    void findAllByUserIdAndCourseId() {
//    }
//
//    @Test
//    void findAllByCourseId() {
//    }
//}