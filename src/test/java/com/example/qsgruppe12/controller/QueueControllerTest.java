package com.example.qsgruppe12.controller;

import com.example.qsgruppe12.dto.JWTRequest;
import com.example.qsgruppe12.model.Course;
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
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//TODO fix the tests

@SpringBootTest(webEnvironment = MOCK)
@AutoConfigureMockMvc
class QueueControllerTest {
    private final String URI = "/queue/"+1L+"/";

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

    Course course;
    private JWTRequest jwtRequest;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();



    @BeforeEach
    void setUp() throws Exception {
//        List<String> ruleList = new ArrayList<>(List.of("6_1_6"));
//        course =
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
//
//        User user = User.builder().id(1L)
//                .firstName("Ola")
//                .lastName("Normann")
//                .email("email@emailsen.no")
//                .password(encoder.encode("hei"))
//                .build();
//        userRepository.save(user);
//        jwtRequest = new JWTRequest(user.getEmail(), user.getPassword());
//
//
//        MvcResult result = mvc.perform(post("/auth/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(String.valueOf((jwtRequest))))
//                .andReturn();
//        String content = result.getResponse().getContentAsString();

    }

    @Test
    void activateCourse() throws Exception {

//                this.mvc.perform(MockMvcRequestBuilders
//                        .get(URI+"active")
//                        .header("Authentication","Bearer "+ jwtRequest)
//                        .content(String.valueOf(1L))
//                        .contentType(MediaType.APPLICATION_JSON))
//                        .andExpect(status().isOk());
//                        .andExpect(jsonPath("$.message").value(""));

    }

    @Test
    void getUsersInQueue() {
    }

    @Test
    void closeQueue() {
    }
}