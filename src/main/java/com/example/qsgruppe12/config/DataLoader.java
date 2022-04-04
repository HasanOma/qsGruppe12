package com.example.qsgruppe12.config;

import com.example.qsgruppe12.model.*;
import com.example.qsgruppe12.repository.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Loads the database with data on application initiation
 */
@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private CourseRepository courseRepository;

    private QueueRepository queueRepository;

    private UserInQueueRepository userInQueueRepository;

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
                      User_CourseRepository userCourseRepository, QueueRepository queueRepository, UserInQueueRepository userInQueue) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.courseRepository = courseRepository;
        this.userCourseRepository = userCourseRepository;
        this.queueRepository = queueRepository;
        this.userInQueueRepository = userInQueue;
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
        userRepository.save(User.builder()
                .id(4L)
                .firstName("Daniel")
                .lastName("Danielsen")
                .email("daniel@gmail.com")
                .password(cryptPasswordEncoder.encode("passord123"))
                .role(roleRepository.getByName("Student"))
                .build());
        Course course = Course.builder()
                .id(1L)
                .code("IDATT2104")
                .name("Nettverksprogrammering")
                .semester("V2022")
                .queueActive(true)
                .build();
        courseRepository.save(course);
        Queue queue = Queue.builder().id(course.getId()).course(course).build();
        UserInQueue userInQueue = UserInQueue.builder()
                .fullName("Anders Tellefsen")
                .courseId(course.getId())
                .localDate(LocalTime.now())
                .message("Test")
                .room("A4-112")
                .spot("1")
                .workNr("Øving 3")
                .workType("Godkjenning")
                .build();
        userInQueueRepository.save(userInQueue);
        UserInQueue userInQueue2 = UserInQueue.builder()
                .fullName("Daniel Danielsen")
                .courseId(course.getId())
                .localDate(LocalTime.now())
                .message("Test 2")
                .room("A4-112")
                .spot("1")
                .workNr("Øving 3")
                .workType("Godkjenning")
                .build();
        userInQueueRepository.save(userInQueue2);
        queueRepository.save(queue);
        course = Course.builder()
                .id(2L)
                .code("IDATT2105")
                .rules(new ArrayList<>(Arrays.asList("6_1_6","2_7_10")))
                .name("Full-Stack Applikasjonsutvikling")
                .semester("V2022")
                .build();
        courseRepository.save(course);
        queue = Queue.builder().id(course.getId()).course(course).build();
        queueRepository.save(queue);
        course = Course.builder()
                .id(3L)
                .code("IFYT1001")
                .name("Fysikk")
                .semester("V2022")
                .build();
        courseRepository.save(course);
        queue = Queue.builder().id(course.getId()).course(course).build();
        queueRepository.save(queue);
    }
}
