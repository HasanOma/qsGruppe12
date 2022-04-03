package com.example.qsgruppe12.controller;

import com.example.qsgruppe12.dto.userdtos.UserLoginDto;
import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.model.Queue;
import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.repository.CourseRepository;
import com.example.qsgruppe12.repository.UserRepository;
import com.example.qsgruppe12.service.course.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;


//TODO fix more tests
@SpringBootTest(webEnvironment = MOCK)
@AutoConfigureMockMvc
class CourseControllerTest {
    private final String URI = "/courses/";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CourseService courseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    CourseController courseController;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    UserLoginDto userLoginDto = new UserLoginDto();


    Course course;
    User user;
    Queue queue;

    @BeforeEach
    void setUp() {
        List<String> ruleList = new ArrayList<>(List.of("6_1_6"));
        course =
                Course.builder()
                        .id(1L)
                        .name("Fullstack applikasjonsutvikling")
                        .code("IDATT2105")
                        .semester("V22")
                        .queueActive(false)
                        .totalWork(6)
                        .rules(ruleList)
                        .nrOfStudents(0)
                        .users(new ArrayList<>())
                        .build();
//        courseRepository.save(course);

    }


    @Test
    public void contextLoads() throws Exception {
        assertNotNull(courseController);
    }

//    @Test
//    void createCourse() throws CourseNotFoundException {
//
//        Authentication auth;
//        CourseRegisterDto courseRegisterDto =
//                CourseRegisterDto.builder()
//                        .name(course.getName())
//                        .code(course.getCode())
//                        .semester(course.getSemester())
//                        .totalWork(course.getTotalWork())
//                        .build();
//
//
////        courseController.createCourse()
////        Mockito.when(courseService.createCourse(courseRegisterDto, "mail"))
////                .thenReturn()
//
//    }

    @WithMockUser
    @Test
    void activateQueue() throws Exception {
//        courseRepository.save(course);
//        List<Long> list = new ArrayList<>(List.of(1L));


//        this.mvc.perform(MockMvcRequestBuilders
//                        .get(URI+"active")
//                        .content(objectMapper.writeValueAsString(list))
//                        .contentType(MediaType.APPLICATION_JSON))
//                        .andExpect(status().isOk())
//                        .andExpect(jsonPath("$.message").value(""));
    }

    @Test
    void updateCourse() {
    }

    @Test
    void activateCourse() {
    }

}