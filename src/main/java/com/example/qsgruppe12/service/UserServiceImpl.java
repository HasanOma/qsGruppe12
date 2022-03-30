package com.example.qsgruppe12.service;

import com.example.qsgruppe12.dto.CourseDto;
import com.example.qsgruppe12.dto.userdtos.RegistrationDto;
import com.example.qsgruppe12.dto.userdtos.UserDto;
import com.example.qsgruppe12.dto.userdtos.UserLoginDto;
import com.example.qsgruppe12.model.Queue;
import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.repository.CourseRepository;
import com.example.qsgruppe12.repository.QueueRepository;
import com.example.qsgruppe12.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private BCryptPasswordEncoder cryptPasswordEncoder;

    private ModelMapper modelMapper;

    private boolean userExistsByEmail(String email){
        return userRepository.findByEmail(email).isPresent();
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
    public List<UserDto> addUsersForCourse(Long courseId, List<RegistrationDto> registrationDto) {
        //TODO if course does not exist or if user exists throw exception
        List<UserDto> savedStudents = new ArrayList<>();
        for (RegistrationDto dto : registrationDto) {
            User student = modelMapper.map(dto, User.class);
            student.setPassword(cryptPasswordEncoder.encode(dto.getPassword()));
            UserDto studentAdded = modelMapper.map(userRepository.save(student), UserDto.class);
            savedStudents.add(studentAdded);
            //TODO send email
        }
        return savedStudents;
    }

    @Override
    public void addTAsForCourse(Long courseId, List<RegistrationDto> registrationDto) {

        for(RegistrationDto dto: registrationDto){
//            if(taRepository.findByEmail(dto.getEmail()).isPresent()){
//                TA ta = taRepository.findByEmail(dto.getEmail()).get();
//                TA_Course ta_course;
//            }
        }
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
