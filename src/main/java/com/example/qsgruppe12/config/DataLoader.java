package com.example.qsgruppe12.config;

import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.model.Role;
import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Loads the database with data on application initiation
 */
@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private CourseRepository courseRepository;

    private User_CourseRepository userCourseRepository;

    private BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();

    /**
     * Constructor of the class.
     * @param userRepository repository of the {@link User} object
     * @param roleRepository repository of the {@link Role} object
     * @param courseRepository repository of the {@link Course} object
     * @param userCourseRepository repository of the User_Course object
     */
    public DataLoader(UserRepository userRepository, RoleRepository roleRepository, CourseRepository courseRepository,
                      User_CourseRepository userCourseRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.courseRepository = courseRepository;
        this.userCourseRepository = userCourseRepository;
    }

    public void run(ApplicationArguments args) {
        roleRepository.save(Role.builder()
                .id(1L)
                .name("Admin")
                .build());
        roleRepository.save(Role.builder()
                .id(2L)
                .name("TA")
                .build());
        roleRepository.save(Role.builder()
                .id(3L)
                .name("Student")
                .build());
        userRepository.save(User.builder()
                .id(1L)
                .firstName("Anders")
                .lastName("Tellefsen")
                .email("andetel@stud.ntnu.no")
                .password(cryptPasswordEncoder.encode("passord123"))
                .role(roleRepository.getByName("Admin"))
                .build());
        userRepository.save(User.builder()
                .id(2L)
                .firstName("Brage")
                .lastName("Minge")
                .email("bragem@stud.ntnu.no")
                .password(cryptPasswordEncoder.encode("passord123"))
                .role(roleRepository.getByName("TA"))
                .build());
        userRepository.save(User.builder()
                .id(3L)
                .firstName("Hasan")
                .lastName("Rehman")
                .email("hasano@stud.ntnu.no")
                .password(cryptPasswordEncoder.encode("passord123"))
                .role(roleRepository.getByName("Student"))
                .build());
        courseRepository.save(Course.builder()
                .code("IDATT2104")
                .name("Nettverksprogrammering")
                .semester("V2022")
                .build());
        courseRepository.save(Course.builder()
                .code("IDATT2105")
                .name("Full-Stack Applikasjonsutvikling")
                .semester("V2022")
                .build());
        courseRepository.save(Course.builder()
                .code("IFYT1001")
                .name("Fysikk")
                .semester("V2022")
                .build());
    }
}
