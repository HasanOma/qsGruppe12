package com.example.qsgruppe12.service;

import com.example.qsgruppe12.dto.CourseDto;
import com.example.qsgruppe12.dto.userdtos.RegistrationDto;
import com.example.qsgruppe12.dto.userdtos.UserDto;
import com.example.qsgruppe12.dto.userdtos.UserLoginDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDto updateUser(Long id, UserDto user);
    List<UserDto> getUsersFromCourse(Long id, CourseDto course);
//    void addUser(RegistrationDto registrationDto);
    List<UserDto> addUsersForCourse(Long courseId, List<RegistrationDto> registrationDto);
    void addTAsForCourse(Long courseId, List<RegistrationDto> registrationDto);

    UserLoginDto getUserLoggingIn(String username);

    UserDto getInQueue(Long courseId, String username);

    List<UserDto> getUsersInQueue(Long courseId);
}
