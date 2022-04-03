package com.example.qsgruppe12.service.user;

import com.example.qsgruppe12.controller.CourseController;
import com.example.qsgruppe12.dto.QueueDto;
import com.example.qsgruppe12.dto.userdtos.*;
import com.example.qsgruppe12.exception.FileNotSupportedException;
import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.model.UserInQueue;
import com.example.qsgruppe12.model.relationship.User_Course;
import com.example.qsgruppe12.model.relationshipkey.UserCourseKey;
import com.example.qsgruppe12.repository.*;
import com.example.qsgruppe12.service.course.CourseService;
import com.example.qsgruppe12.service.email.EmailService;
import com.example.qsgruppe12.util.RequestResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.orm.jpa.EntityManagerProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Implimentation class of {@link UserService}
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

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
    private EmailService emailService;

    private BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();

    private ModelMapper modelMapper = new ModelMapper();

    private boolean userExistsByEmail(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    /**
     * Generates a random string if the password is empty.
     * @return returns a random char string.
     */
    private String randomStringGenerator(){
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        return RandomStringUtils.random( 20, s );
    }

    /**
     * Method to update user.
     * @param id id of the user in the repository.
     * @param user the user changing his alt email or password.
     * @return Returns a UserDto.
     */
    @Override
    public UserDto updateUser(Long id, UserUpdateDto user) {
        //TODO check if logged in is user requesting for themself
        User userUpdate = userRepository.getById(id);
        userUpdate.setAltEmail(user.getAltEmail());
        if(user.getPassword() !=null || !user.getPassword().isBlank()){
            userUpdate.setPassword(cryptPasswordEncoder.encode(user.getPassword()));
        }
        userRepository.save(userUpdate);
        log.info("User with id {} updated to the database",userUpdate.getId());
        return modelMapper.map(userUpdate, UserDto.class);
    }

    /**
     * Method adds all users in specific course and returns them
     * @param courseId course to retrieve users from
     * @return returns list of UserDto in specific course
     */
    @Override
    public List<UserDto> getUsersFromCourse(Long courseId) {
        //TODO permission to get all users from course
        List<User> users = userRepository.findAllById(userCourseRepository.findAllByCourseId(courseId));
        List<UserDto> usersInCourse = new ArrayList<>();
        for (User user: users){
            usersInCourse.add(modelMapper.map(user, UserDto.class));
        }
        log.info("List of users from course with id {} returned",courseId);
        return usersInCourse;
    }

    /**
     * Method to add user in the repository.
     * @param registrations list of users.
     * @return returns confirmation to the client.
     */
    @Override
    public RequestResponse createUser(List<UserRegistrationDto> registrations){
        for (int i = 0; i < registrations.size(); i++) {
            User user = modelMapper.map(registrations.get(i), User.class);
            String message = "Congratulations you are now registered in QS!\n";
            sendMailOnCreation(setPassword(registrations, i, user), user.getEmail(), message);
            userRepository.save(user);
        }
        log.info("Saved {} users to the repository", registrations.size());
        return new RequestResponse("User(s) created");
    }

    /**
     * Method to create users reading them from a file.
     * @param file file to read users from.
     * @return returns a response if.
     * @throws IOException File reading throws an error.
     */
    @Override
    public RequestResponse createUser(MultipartFile file) throws IOException {
        log.info("File {} received to add users from.",file.getContentType());
        List<User> users = handleFile(0L,file);
        if (users != null) {
            userRepository.saveAll(users);
            log.info("Saved {} users to the repository", users.size());
            return new RequestResponse("Users added successfully");
        }
        return new RequestResponse(new FileNotSupportedException());
    }

    /**
     * Method to add user for a specific course from a file
     * @param courseId course id to add users to
     * @param file file to add users from
     * @return return a
     * @throws IOException
     */
    @Override
    public RequestResponse addUsersForCourse(Long courseId, MultipartFile file) throws IOException {
        log.info("File {} received to add users from.",file.getContentType());
        handleFile(courseId, file);
        return new RequestResponse("Users added successfully for " +
                courseRepository.getById(courseId).getCode() + courseRepository.getById(courseId).getCode());
    }

    /**
     * Adds list of users to a specific course.
     * @param courseId id of the course.
     * @param registrations list of users.
     * @return list of registered users.
     */
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
                        String message = "You are now added in the Course " +
                                course.getCode() + " " + course.getName() + "\n";
                        sendMailOnCreation("your old one ",user.getEmail(), message);
                    }
                }
            } else {
                String message = "Congratulations you are now registered in QS!\n" +
                        "You are now added in the Course " + course.getCode() + " " + course.getName() + "\n";
                sendMailOnCreation(setPassword(registrations, i, user), user.getEmail(), message);
                addUserRelationship(savedUsers, course, user);
                savedUsers.get(i).setUserRoleName(registrations.get(i).getUserRoleName());
            }
        }
        log.info("Saved {} users to the repository.",savedUsers.size());
        log.info("Saved to course with id {}", courseId);
        return savedUsers;
    }

    /**
     * Adds List of users already in the database to a specific course.
     * @param courseId id of the course.
     * @param userEmailsDto emails of the users in the database.
     * @return confirmation to the client.
     */
    @Override
    public RequestResponse addExistingUserToCourse(Long courseId, List<UserEmailsDto> userEmailsDto) {
        List<UserDto> savedUsers = new ArrayList<>();
        Course course = courseRepository.getById(courseId);
        for (int i = 0; i < userEmailsDto.size(); i++) {
            User user = modelMapper.map(userEmailsDto.get(i), User.class);
            addUserRelationship(savedUsers, course, user);
            savedUsers.get(i).setUserRoleName(user.getUserRoleName());
            String message = "You are now added in the Course " +
                    course.getCode() + " " + course.getName() + "\n";
            sendMailOnCreation("your old one ",user.getEmail(), message);
        }
        log.info("Users added to course with id {} ",courseId);
        return new RequestResponse("Users added to " + course.getCode() + " ");
    }

    /**
     * Helper Method to send a mail.
     * @param password password created for the new user.
     * @param email email of the user being created.
     * @param message message to the user.
     */
    private void sendMailOnCreation(String password, String email, String message){
        message += "email: " + email +"\n password: " + password;
        try {
            emailService.sendEmail("QS",email,"New user in QS", message);
        } catch (Exception e){
            e.printStackTrace();
            //Own throw method
            log.debug("[X] Email was not sent to user with email {}", email);
        }
    }

    /**
     * Method that sets and encrypts the password of the user.
     * @param registrations list of user information to register.
     * @param i index of the loop.
     * @param user user object.
     */
    private String setPassword(List<UserRegistrationDto> registrations, int i, User user) {
        String password = registrations.get(i).getPassword();

        if(password.isBlank()){
            password = randomStringGenerator();
        }

        user.setPassword(password);
        user.setPassword(cryptPasswordEncoder.encode(password));
        user.setRole(roleRepository.getByName(registrations.get(i).getUserRoleName()));
        return password;
    }

    /**
     * Method that adds the relationship of the course and user.
     * @param savedUsers users saved to the database.
     * @param course course to create relationship with.
     * @param user user to create relationship with.
     */
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

    /**
     * Login method that checks if the user exists and matches a user in the database.
     * @param login user credentials.
     * @return returns all information the client side needs to save in state.
     */
    @Override
    public UserLoginReturnDto getUserLoggingIn(UserLoginDto login) {
        if (!userExistsByEmail(login.getEmail())) {
            //throw exception
            log.info("Did not find user with email {} ", login.getEmail());
            return null;
        }
        if (!cryptPasswordEncoder.matches(login.getPassword(), userRepository.findByEmail(login.getEmail()).get().getPassword())){
            //throw exception
            log.debug("[X] Password check failed for user with email {}", login.getEmail());
            return null;
        }
        userRepository.findByEmail(login.getEmail()).isPresent();
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

    /**
     * Method to send user a new password if they forgot.
     * @param userForgotPassword email of the user.
     * @return returns conditionally whether user exists in database.
     */
    @Override
    public RequestResponse forgotPassword(UserForgotPassword userForgotPassword){
        if (userRepository.findByEmail(userForgotPassword.getEmail()).isPresent()){
            User user = userRepository.findByEmail(userForgotPassword.getEmail()).get();
            String message = "Nytt passord er lagd for deg. \n";
            String password = randomStringGenerator();
            sendMailOnCreation(password,userForgotPassword.getEmail(), message);
            user.setEmail(password);
            userRepository.save(user);
            return new RequestResponse("Your new password is now sent to your email!");
        }
        return new RequestResponse("Your email is not registered in our database!");
    }

    /**
     * Method to find out if file is a .csv file and read in and create users out of that.
     * @param courseId course id.
     * @param file file to read in users from.
     * @return returns a list of users that have been added to a specific course or will be.
     * @throws IOException Filereader throws exception.
     */
    public List<User> handleFile(Long courseId, MultipartFile file) throws IOException {
        String fileType = Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];
        if (!fileType.equalsIgnoreCase("csv")){
//            throw new FileNotSupportedException("File is not a csv file");
            List<User> userss = null;
            return userss;
        }
        List<User> users = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader((File) file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] variable = line.split(",");
            User userToAdd = User.builder()
                    .lastName(variable[0])
                    .firstName(variable[1])
                    .email(variable[2])
                    .role(roleRepository.getByName("Student"))
                    .build();
            users.add(userToAdd);
        }
        if (courseId != 0){
            Course course = courseRepository.getById(courseId);
            for(User user : users){
                course.setNrOfStudents(course.getNrOfStudents()+1);
                courseRepository.save(course);
                long nrOfStudents = userRepository.findAll().size();
                UserCourseKey userCourseKey = new UserCourseKey();
                userCourseKey.setUserId(nrOfStudents);
                userCourseKey.setCourseId(courseId);
                User_Course userCourse = User_Course.builder()
                        .userCourseKey(userCourseKey)
                        .course(course)
                        .user(user)
                        .workApproved("")
                        .build();
                user.getCourses().add(userCourse);
                userCourseRepository.save(userCourse);
                userRepository.save(user);
            }
        }
        return users;
    }
}