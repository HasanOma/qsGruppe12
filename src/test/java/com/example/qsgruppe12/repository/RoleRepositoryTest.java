package com.example.qsgruppe12.repository;

import com.example.qsgruppe12.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
//class RoleRepositoryTest {
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @BeforeEach
//    void setUp() {
//        Role role = Role.builder().name("Student").id(0L).build();
//        entityManager.persist(entityManager.merge(role));
//    }
//
//    @Test
//    void getByName() {
//        Role role = roleRepository.getByName("Student");
//        assertEquals("Student", role.getName());
//    }
//
//    @Test
//    void getByWrongName(){
//        assertNull(roleRepository.getByName("Rolle Rollesen"));
//    }
//
//}