package com.example.qsgruppe12.config;

import com.example.qsgruppe12.model.Role;
import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private CourseRepository courseRepository;

//  spot  private RoomRepository roomRepository;

    private User_CourseRepository userCourseRepository;

    public DataLoader(UserRepository userRepository, RoleRepository roleRepository, CourseRepository courseRepository,
                      User_CourseRepository userCourseRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.courseRepository = courseRepository;
        this.userCourseRepository = userCourseRepository;
    }

    public void run(ApplicationArguments args) {
        roleRepository.save(Role.builder()
                .id(1L).name("Admin").build());
        roleRepository.save(Role.builder()
                .id(2L).name("TA").build());
        roleRepository.save(Role.builder()
                .id(3L).name("Student").build());
//        userRepository.save(User.builder()
//                .id(1L).firstName("Ola").lastName("Normann").email("Ola@Normann.no").password("passord").role().build();
    }
}
