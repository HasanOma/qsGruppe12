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
        for (RegistrationDto dto : registrations) {

            User student = modelMapper.map(dto, User.class);

            String password = dto.getPassword();

            if(password.isBlank()){
                password = randomStringGenerator();
            }

            student.setPassword(password);
            student.setPassword(cryptPasswordEncoder.encode(password));
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
        List<User_Course> userCourseList = userCourseRepository.findAll();
        for (int i = 0; i < registrations.size(); i++) {
            User student = modelMapper.map(registrations.get(i), User.class);
            student.setCourses(new ArrayList<>());
            if (userRepository.findByEmail(student.getEmail()).isPresent()){
                if (userCourseList.stream().findAny().isPresent()){
                    if(!userCourseList.stream().findAny().get().getUser().getEmail().equalsIgnoreCase(student.getEmail())){
                        addUserRelationship(i, savedStudents, course, student);
                    }
                }
            } else {
                String password = registrations.get(i).getPassword();
                if(password.isBlank()){
                    password = randomStringGenerator();
                }
                student.setPassword(password);
                student.setPassword(cryptPasswordEncoder.encode(password));

                student.setRole(roleRepository.getById((long) 3));
                addUserRelationship(i, savedStudents, course, student);

            }

            //TODO send email plus password with it
        }
        return savedStudents;
    }

    @Override
    public List<UserDto> addTAsForCourse(Long courseId, List<RegistrationDto> registrations) {
//TODO if course does not exist or if user exists throw exception

        List<UserDto> tas = new ArrayList<>();
        Course course = courseRepository.getById(courseId);
        List<User_Course> userCourseList = userCourseRepository.findAll();
        for (int i = 0; i < registrations.size(); i++) {

            User ta = modelMapper.map(registrations.get(i), User.class);
            ta.setCourses(new ArrayList<>());
            if (userRepository.findByEmail(ta.getEmail()).isPresent()){
                if(!userCourseList.stream().findAny().get().getUser().getEmail().equalsIgnoreCase(ta.getEmail())){

                    addUserRelationship(i, tas, course, ta);
                }
            } else {
                String password = registrations.get(i).getPassword();

                if(password.isBlank()){
                    password = randomStringGenerator();
                }

                ta.setPassword(password);

                ta.setPassword(cryptPasswordEncoder.encode(password));

                ta.setRole(roleRepository.getById((long) 2));
                //TODO add course as well
                addUserRelationship(i, tas, course, ta);
            }
            //TODO send email
        }
        return tas;
    }

    private void addUserRelationship(int i, List<UserDto> tas, Course course, User ta) {
        UserDto studentAdded = modelMapper.map(userRepository.save(ta), UserDto.class);
        tas.add(studentAdded);
        course.setNrOfStudents(course.getNrOfStudents()+1);

        long nrOfStudents = userRepository.findAll().size();
        UserCourseKey userCourseKey = new UserCourseKey();
        userCourseKey.setUserId(nrOfStudents);
        userCourseKey.setCourseId(course.getId());
        User_Course userCourse = User_Course.builder().userCourseKey(userCourseKey).course(course).user(ta).workApproved("").build();
        ta.getCourses().add(userCourse);
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
        //        int nrOfCourses = userCourseRepository.findAll(Sort.by(userFromDB));
//
//        user.getCourses().addAll(courseRepository.getById(userCourseRepository.getCourseIdByUserId()));
        return modelMapper.map(userFromDB, UserLoginReturnDto.class);
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
