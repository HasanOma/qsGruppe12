package com.example.qsgruppe12.service.course;

import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.model.Role;
import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.model.relationship.User_Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class CourseServiceImplTest {


    private User user = User.builder().userRoleName("Student").id(1L).role(Role.builder().name("student").id(1L).build()).email("tull@gmail.com").firstName("Ola").lastName("Nordmann").password("olanordmann").build();


    private Course course =
            Course.builder()
            .id(1L)
                .name("Fullstack applikasjonsutvikling")
                .code("IDATT2105")
                .semester("V22")
                .queueActive(false)
                .totalWork(6)
                .rules("6_1_6")
                .nrOfStudents(100)
                .build();;



    @BeforeEach
    void setUp() {
    }

    @Test
    void createCourse() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteCourse() {
    }

    @Test
    void validWorkShouldGiveTrue() {
//        User_Course uc = User_Course.builder().course(course).user(user).workApproved("111111").build();
//        assertTrue(uc.isCanDoExam());
    }

    @Test
    void invalidWorkShouldGiveFalse(){

    }

    @Test
    void activateCourseQueue() {
    }

    @Test
    void deActivateCourse() {
    }
}