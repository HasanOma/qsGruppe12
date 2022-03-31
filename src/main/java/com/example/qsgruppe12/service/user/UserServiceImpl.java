package com.example.qsgruppe12.service.user;

import com.example.qsgruppe12.dto.CourseDto;
import com.example.qsgruppe12.dto.userdtos.LoginDto;
import com.example.qsgruppe12.dto.userdtos.RegistrationDto;
import com.example.qsgruppe12.dto.userdtos.UserDto;
import com.example.qsgruppe12.dto.userdtos.UserLoginReturnDto;
import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.model.Queue;
import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.model.relationship.User_Course;
import com.example.qsgruppe12.model.relationshipkey.UserCourseKey;
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
public class UserServiceImpl implements UserService {

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

    private BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();

    private ModelMapper modelMapper = new ModelMapper();

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
        for (int i = 0; i < registrations.size(); i++) {

            User student = modelMapper.map(registrations.get(i), User.class);

            setPassword(registrations, i, student);
            userRepository.save(student);

            //TODO send email plus password with it

        }
        return new RequestResponse("User created");
    }

    @Override
    public List<UserDto> addUsersForCourse(Long courseId, List<RegistrationDto> registrations) {

        List<UserDto> savedStudents = new ArrayList<>();
        Course course = courseRepository.getById(courseId);
        List<User_Course> userCourseList = userCourseRepository.findAll();
        for (int i = 0; i < registrations.size(); i++) {
            User student = modelMapper.map(registrations.get(i), User.class);
            student.setCourses(new ArrayList<>());
            if (userRepository.findByEmail(student.getEmail()).isPresent()){
                if (userCourseList.stream().findAny().isPresent()){
                    if(!userCourseList.stream().findAny().get().getUser().getEmail().equalsIgnoreCase(student.getEmail())){
                        addUserRelationship(savedStudents, course, student);
                    }
                }
            } else {
                setPassword(registrations, i, student);
                addUserRelationship(savedStudents, course, student);
            }

            //TODO send email plus password with it
        }
        return savedStudents;
    }

    private void setPassword(List<RegistrationDto> registrations, int i, User student) {
        String password = registrations.get(i).getPassword();

        if(password.isBlank()){
            password = randomStringGenerator();
        }

        student.setPassword(password);
        student.setPassword(cryptPasswordEncoder.encode(password));
        student.setRole(roleRepository.getByName(registrations.get(i).getUserRoleName()));
    }

    private void addUserRelationship(List<UserDto> tas, Course course, User user) {
        User userSaved = userRepository.save(user);
        UserDto studentAdded = modelMapper.map(userSaved, UserDto.class);
        tas.add(studentAdded);
        course.setNrOfStudents(course.getNrOfStudents()+1);

        long nrOfStudents = userRepository.findAll().size();
        UserCourseKey userCourseKey = new UserCourseKey();
        userCourseKey.setUserId(nrOfStudents);
        userCourseKey.setCourseId(course.getId());
        User_Course userCourse = User_Course.builder().userCourseKey(userCourseKey).course(course).user(user).workApproved("").build();
        user.getCourses().add(userCourse);
        userCourseRepository.save(userCourse);
    }

    @Override
    public UserLoginReturnDto getUserLoggingIn(LoginDto login) {
        if (!userExistsByEmail(login.getEmail())) {
            //throw exception
            return null;
        }
        if (!cryptPasswordEncoder.matches(login.getPassword(), userRepository.findByEmail(login.getEmail()).get().getPassword())){
            //throw exception
            return null;
        }
        User userFromDB = userRepository.findByEmail(login.getEmail()).get();
        UserLoginReturnDto returnUser = modelMapper.map(userFromDB, UserLoginReturnDto.class);

        List<User_Course> userCourseList = userCourseRepository.findAll();
        for (int i = 0; i < userCourseList.size(); i++) {
            if(userCourseList.get(i).getUser().getEmail().equalsIgnoreCase(returnUser.getEmail())){
                returnUser.getCourses().add(modelMapper.map(userCourseList.get(i).getCourse(), CourseDto.class));
            }
        }
        return returnUser;
    }

    @Override
    public UserDto getInQueue(Long courseId, String email) {
        if (queueRepository.getByCourseId(courseId).isPresent() && courseRepository.getById(courseId).isArchived()){
            Queue queue = queueRepository.getByCourseId(courseId).get();
            if(userRepository.findByEmail(email).isPresent()){
                queue.getUsersInQueue().add(userRepository.findByEmail(email).get());
            }
            return null;
        }else {
            return null;
        }
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
