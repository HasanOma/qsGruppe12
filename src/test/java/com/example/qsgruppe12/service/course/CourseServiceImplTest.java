package com.example.qsgruppe12.service.course;

import com.example.qsgruppe12.dto.userdtos.UserDto;
import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.model.Role;
import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.model.relationship.User_Course;
import com.example.qsgruppe12.service.user.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


//@SpringBootTest
//class CourseServiceImplTest {
//
//
//    List<String> ruleList = new ArrayList<>(List.of("6_1_6"));
//
//    private User user = User.builder()
//            .userRoleName("Student")
//            .id(1L)
//            .role(Role.builder()
//            .name("student")
//            .id(1L)
//            .build())
//            .email("tull@gmail.com")
//            .firstName("Ola")
//            .lastName("Nordmann")
//            .password("olanordmann")
//            .courses(new ArrayList<>())
//            .build();
//
////    private ModelMapper modelMapper = new ModelMapper();
//
//    @Autowired
//    UserServiceImpl usi;
//
//    private Course course =
//            Course.builder()
//            .id(1L)
//                .name("Fullstack applikasjonsutvikling")
//                .code("IDATT2105")
//                .semester("V22")
//                .queueActive(false)
//                .totalWork(6)
//                .rules(ruleList)
//                .nrOfStudents(100)
//                .users(new ArrayList<>())
//                .build();
//
//    @Autowired
//    CourseServiceImpl imp;
//
//
//    @BeforeEach
//    void setUp() {
//
//    }
//
//    @Test
//    void createCourse() {
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void deleteCourse() {
//    }
//
//    @Test
//    void validWorkShouldGiveTrue() {
////        User_Course uc = User_Course.builder().course(course).user(user).workApproved("111111").build();
////        user.getCourses().add(uc);
////        course.getUsers().add(uc);
////        UserDto userDto = modelMapper.map(user, UserDto.class);
////        usi.addUserRelationship(new ArrayList<>(List.of(userDto)),course, user);
////
////        int tall = imp.checkExamStatus(1L);
////        assertEquals(1, tall);
////        boolean test = uc.isCanDoExam();
////        assertTrue(test);
//    }
//
//    @Test
//    void invalidWorkShouldGiveFalse(){
//        User_Course uc = User_Course.builder().course(course).user(user).workApproved("000000").build();
//
//        boolean test = uc.isCanDoExam();
//        assertFalse(test);
//
//    }
//
//    @Test
//    void activateCourseQueue() {
//
//    }
//
//    @Test
//    void deActivateCourse() {
//    }
//}