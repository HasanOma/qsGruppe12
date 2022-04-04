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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;


//TODO fix more tests
@SpringBootTest(webEnvironment = MOCK)
@AutoConfigureMockMvc
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
//
    @Autowired
    private CourseController courseController;

    @Autowired
    private CourseRepository courseRepository;

    private String COURSE_REGISTER_DTO_STRING =
            "{" +
                "\"name\":\"Nettverksprogrammering\"," +
                "\"code\":\"IDATT2104\"," +
                "\"semester\":\"V2022\"," +
                "\"totalWork\":6\"," +
                "\"rules\":\"[\"1_2_3\"]\"," +
            "}";

//    @BeforeEach
//    void setUp() {
//        List<String> ruleList = new ArrayList<>(List.of("6_1_6"));
//        Course course =
//                Course.builder()
//                        .id(1L)
//                        .name("Fullstack applikasjonsutvikling")
//                        .code("IDATT2105")
//                        .semester("V22")
//                        .queueActive(false)
//                        .totalWork(6)
//                        .rules(ruleList)
//                        .nrOfStudents(0)
//                        .users(new ArrayList<>())
//                        .build();
//        courseRepository.save(course);
//    }

//    @Test
//    @DisplayName("Check that course controller is not null after being autowired")
//    public void contextLoads() throws Exception {
//        assertThat(courseController).isNotNull();
//    }
//
//    @Test
//    @DisplayName("Check that student_courses get mapping returns status OK")
//    public void checkThatStudentCourseReturnsStatusOK() throws Exception {
//        mockMvc.perform(get("/courses/student_courses"))
//                .andExpect(status().isOk());
//    }

//    @Test
//    @DisplayName("Check if a course is created if post to add")
//    public void checkThatCourseIsCreated() throws Exception {
//        mockMvc.perform(post("/courses/add")
//                        .with(csrf())
//                        .content(COURSE_REGISTER_DTO_STRING))
//                .andExpect(status().isCreated());
//    }

//    @Test
//    @DisplayName("Check that status OK is returned when performing get request to active")
//    public void checkThatCourseActiveReturnsOK() throws Exception {
//        String email = "example@example.com";
//
//        mockMvc.perform(get("/courses/active").param("email", email))
//                .andExpect(status().isOk());
//    }





//

//

//
//    UserLoginDto userLoginDto = new UserLoginDto();
//
//
//    Course course;
//    User user;
//    Queue queue;






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

//    @WithMockUser
//    @Test
//    void activateQueue() throws Exception {
////        courseRepository.save(course);
////        List<Long> list = new ArrayList<>(List.of(1L));
//
//
////        this.mvc.perform(MockMvcRequestBuilders
////                        .get(URI+"active")
////                        .content(objectMapper.writeValueAsString(list))
////                        .contentType(MediaType.APPLICATION_JSON))
////                        .andExpect(status().isOk())
////                        .andExpect(jsonPath("$.message").value(""));
//    }
//
//    @Test
//    void updateCourse() {
//    }
//
//    @Test
//    void activateCourse() {
//    }

}