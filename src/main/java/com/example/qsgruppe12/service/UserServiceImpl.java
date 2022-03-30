package com.example.qsgruppe12.service;

import com.example.qsgruppe12.dto.CourseDto;
import com.example.qsgruppe12.dto.userdtos.RegistrationDto;
import com.example.qsgruppe12.dto.userdtos.UserDto;
import com.example.qsgruppe12.dto.userdtos.UserLoginDto;
import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.model.Queue;
import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.model.relationship.User_Course;
import com.example.qsgruppe12.repository.*;
import com.example.qsgruppe12.util.RequestResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    //TODO email sent to users created

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private User_CourseRepository userCourseRepository;

    @Autowired
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder cryptPasswordEncoder;

    private ModelMapper modelMapper;

    private boolean userExistsByEmail(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    private String randomStringGenerator(){
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        return RandomStringUtils.random( 20, s );
    }

    @Override
    public UserDto updateUser(Long id, UserDto user) {
//        User userUpdate = teacherRepository.getById(id).orElse(taRepository.getById(id).orElse(studentRepository.getById(id)));
        return user;
    }

    @Override
    public List<UserDto> getUsersFromCourse(Long id, CourseDto course) {
        return null;
    }

    @Override
    public RequestResponse createUser(List<RegistrationDto> registrations){
        for (RegistrationDto dto : registrations) {

            User student = modelMapper.map(dto, User.class);

            String password = dto.getPassword();

            if(password.isBlank()){
                password = randomStringGenerator();
            }

            student.setPassword(password);
            student.setPassword(cryptPasswordEncoder.encode(dto.getPassword()));
            student.setRole(roleRepository.getById((long) 3));
            userRepository.save(student);

            //TODO send email plus password with it

        }
        return new RequestResponse("User created");
    }

    @Override
    public List<UserDto> addUsersForCourse(Long courseId, List<RegistrationDto> registrations) {
        //TODO if course does not exist or if user exists throw exception

        List<UserDto> savedStudents = new ArrayList<>();
        Course course = courseRepository.getById(courseId);
        for (RegistrationDto dto : registrations) {

            User student = modelMapper.map(dto, User.class);

            String password = dto.getPassword();

            if(password.isBlank()){
                password = randomStringGenerator();
            }

            student.setPassword(password);

            student.setPassword(cryptPasswordEncoder.encode(dto.getPassword()));

            student.setRole(roleRepository.getById((long) 3));

            User_Course userCourse = User_Course.builder().course(course).user(student).build();
            userCourseRepository.save(userCourse);

            UserDto studentAdded = modelMapper.map(userRepository.save(student), UserDto.class);
            savedStudents.add(studentAdded);


            //TODO send email plus password with it
        }
        return savedStudents;
    }

    @Override
    public List<UserDto> addTAsForCourse(Long courseId, List<RegistrationDto> registrations) {
//TODO if course does not exist or if user exists throw exception

        List<UserDto> tas = new ArrayList<>();
        Course course = courseRepository.getById(courseId);
        for (RegistrationDto dto : registrations) {

            User ta = modelMapper.map(dto, User.class);

            String password = dto.getPassword();

            if(password.isBlank()){
                password = randomStringGenerator();
            }

            ta.setPassword(password);

            ta.setPassword(cryptPasswordEncoder.encode(dto.getPassword()));

            ta.setRole(roleRepository.getById((long) 2));

            User_Course userCourse = User_Course.builder().course(course).user(ta).workApproved(null).build();
            userCourseRepository.save(userCourse);

            UserDto studentAdded = modelMapper.map(userRepository.save(ta), UserDto.class);
            tas.add(studentAdded);


            //TODO send email
        }
        return tas;
    }

    @Override
    public UserLoginDto getUserLoggingIn(String email) {
        if (userExistsByEmail(email)) {
            User userFromDB = userRepository.findByEmail(email).get();
            UserLoginDto user = modelMapper.map(userFromDB, UserLoginDto.class);
        }
        return null;
    }

    @Override
    public UserDto getInQueue(Long courseId, String username) {


        return null;
    }

    @Override
    public List<UserDto> getUsersInQueue(Long courseId) {
        if (!courseRepository.getById(courseId).isQueueActive()){
            return null;
        }
        Queue queue = queueRepository.getById(courseRepository.getById(courseId).getQueue().getId());
        List<UserDto> usersInQueue = new ArrayList<>();
        for (int i = 0; i < queue.getUsersInQueue().size(); i++) {
            usersInQueue.add(modelMapper.map(queue.getUsersInQueue().get(i), UserDto.class));
        }
        return usersInQueue;
    }
}
