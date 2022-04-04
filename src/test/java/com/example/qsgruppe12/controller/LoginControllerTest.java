package com.example.qsgruppe12.controller;

import com.example.qsgruppe12.config.JWTConfig;
import com.example.qsgruppe12.dto.JWTRequest;
import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.x.protobuf.Mysqlx;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = MOCK)
@AutoConfigureMockMvc
class LoginControllerTest {

    private String URI = "/auth";
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JWTConfig jwtConfig;

    @Autowired
    private UserRepository userRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private JWTRequest jwtRequest;

    @BeforeEach
    void setUp() {
        User user = User.builder().id(1L)
                .firstName("Ola")
                .lastName("Normann")
                .email("email@emailsen.no")
                .password(encoder.encode("hei"))
                .build();
        userRepository.save(user);
        jwtRequest = new JWTRequest(user.getEmail(), user.getPassword());

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @WithMockUser
    void authenticate() throws Exception {

//        mockMvc.perform(post(URI + "/login")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(String.valueOf((jwtRequest))));

        mockMvc.perform(
                post(URI + "/login")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer token"))
                .andExpect(status().isOk());
    }

    @Test
    void forgotPassword() {
    }
}

