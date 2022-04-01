package com.example.qsgruppe12.service.user;

import com.example.qsgruppe12.dto.QueueDto;
import com.example.qsgruppe12.dto.userdtos.*;
import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.model.UserInQueue;
import com.example.qsgruppe12.model.relationship.User_Course;
import com.example.qsgruppe12.model.relationshipkey.UserCourseKey;
import com.example.qsgruppe12.repository.*;
import com.example.qsgruppe12.service.course.CourseService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    //TODO email sent to users created
    //TODO return just courseIdDTO when logging in

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

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserInQueueRepository userInQueueRepository;

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
    public UserDto updateUser(Long id, UserUpdateDto user) {
        //TODO check if logged in is user requesting for themself
        User userUpdate = userRepository.getById(id);
        userUpdate.setAltEmail(user.getAltEmail());
        userUpdate.setPassword(cryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(userUpdate);
        return modelMapper.map(userUpdate, UserDto.class);
    }

    @Override
    public List<UserDto> getUsersFromCourse(Long courseId) {
        //TODO permission to get all users from course
        List<User> users = userRepository.findAllById(userCourseRepository.findAllByCourseId(courseId));
        List<UserDto> usersInCourse = new ArrayList<>();
        for (User user: users){
            usersInCourse.add(modelMapper.map(user, UserDto.class));
        }
        return usersInCourse;
    }

    @Override
    public RequestResponse createUser(List<UserRegistrationDto> registrations){
        for (int i = 0; i < registrations.size(); i++) {

            User student = modelMapper.map(registrations.get(i), User.class);

            setPassword(registrations, i, student);
            userRepository.save(student);

            //TODO send email plus password with it

        }
        return new RequestResponse("User created");
    }

    @Override
    public List<UserDto> addUsersForCourse(Long courseId, List<UserRegistrationDto> registrations) {

        List<UserDto> savedUsers = new ArrayList<>();
        Course course = courseRepository.getById(courseId);
        List<User_Course> userCourseList = userCourseRepository.findAll();
        for (int i = 0; i < registrations.size(); i++) {
            User user = modelMapper.map(registrations.get(i), User.class);
            user.setCourses(new ArrayList<>());
            if (userRepository.findByEmail(user.getEmail()).isPresent()){
                if (userCourseList.stream().findAny().isPresent()){
                    if(!userCourseList.stream().findAny().get().getUser().getEmail().equalsIgnoreCase(user.getEmail())){
                        addUserRelationship(savedUsers, course, user);
                    }
                }
            } else {
                setPassword(registrations, i, user);
                addUserRelationship(savedUsers, course, user);
                savedUsers.get(i).setUserRoleName(registrations.get(i).getUserRoleName());
            }
            //TODO send email plus password with it
        }
        return savedUsers;
    }

    @Override
    public RequestResponse addExistingUserToCourse(Long courseId, List<UserEmailsDto> userEmailsDto) {
        List<UserDto> savedUsers = new ArrayList<>();
        Course course = courseRepository.getById(courseId);
        for (int i = 0; i < userEmailsDto.size(); i++) {
            User user = modelMapper.map(userEmailsDto.get(i), User.class);
            addUserRelationship(savedUsers, course, user);
            savedUsers.get(i).setUserRoleName(user.getUserRoleName());
        }
        return new RequestResponse("Users added to " + course.getCode() + " ");
    }

    private void setPassword(List<UserRegistrationDto> registrations, int i, User student) {
        String password = registrations.get(i).getPassword();

        if(password.isBlank()){
            password = randomStringGenerator();
        }

        student.setPassword(password);
        student.setPassword(cryptPasswordEncoder.encode(password));
//        student.setFirstName(roleRepository.getByName(registrations.get(i).getUserRoleName()).getName());
        student.setRole(roleRepository.getByName(registrations.get(i).getUserRoleName()));
    }

    private void addUserRelationship(List<UserDto> savedUsers, Course course, User user) {
        User userSaved = userRepository.save(user);
        UserDto studentAdded = modelMapper.map(userSaved, UserDto.class);
        savedUsers.add(studentAdded);
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
    public UserLoginReturnDto getUserLoggingIn(UserLoginDto login) {
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
        System.out.println(userFromDB.getRole().getId());
        List<User_Course> userCourseList = userCourseRepository.findAll();
        for (int i = 0; i < userCourseList.size(); i++) {
            if(userCourseList.get(i).getUser().getEmail().equalsIgnoreCase(returnUser.getEmail())){

                returnUser.getCourses().get(i).setCode(userCourseList.get(i).getCourse().getCode());
                returnUser.getCourses().get(i).setId(userCourseList.get(i).getCourse().getId());
                returnUser.getCourses().get(i).setArchived(userCourseList.get(i).getCourse().isArchived());
                returnUser.getCourses().get(i).setRules(userCourseList.get(i).getCourse().getRules());
                returnUser.getCourses().get(i).setName(userCourseList.get(i).getCourse().getName());

                if (userFromDB.getUserRoleName().equalsIgnoreCase("Admin")){
                    returnUser.getCourses().get(i).setNrOfStudents(userCourseList.get(i).getCourse().getNrOfStudents());
                    returnUser.getCourses().get(i).setExamReady(courseService
                            .checkExamStatus(userCourseList.get(i).getCourse().getId()));
                }

            }
        }
        return returnUser;
    }

    @Override
    public UserDto getInQueue(Long courseId, UserGetInQueueDto queueDto) {
        User user = userRepository.getById(queueDto.getUserId());
        if (queueRepository.getByCourseId(courseId).isPresent() && courseRepository.getById(courseId).isQueueActive()){
            if(userRepository.findById(queueDto.getUserId()).isPresent()){
                UserInQueue userInQueue = modelMapper.map(queueDto, UserInQueue.class);
                userInQueue.setFullName(user.firstName + user.getLastName());
                userInQueue.setCourseId(courseId);
                userInQueue.setLocalDate(LocalDate.now());
                userInQueueRepository.save(userInQueue);
            }
        }
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public List<QueueDto> getUsersInQueue(Long courseId) {
        if (!courseRepository.getById(courseId).isQueueActive()){
            return null;
        }
        List<UserInQueue> queue = userInQueueRepository.getByCourseId(courseId);
        List<QueueDto> usersInQueue = new ArrayList<>();
        for (UserInQueue userInQueue : queue) {
            usersInQueue.add(modelMapper.map(userInQueue, QueueDto.class));
        }
        return usersInQueue;
    }
}