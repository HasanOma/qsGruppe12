package com.example.qsgruppe12.service.user;

import com.example.qsgruppe12.dto.QueueDto;
import com.example.qsgruppe12.dto.userdtos.*;
import com.example.qsgruppe12.util.RequestResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDto updateUser(Long id, UserUpdateDto user);

    List<UserDto> getUsersFromCourse(Long courseId);

//    void addUser(RegistrationDto registrationDto);

    RequestResponse createUser(List<UserRegistrationDto> registrations);

    List<UserDto> addUsersForCourse(Long courseId, List<UserRegistrationDto> registrationDto);

    UserLoginReturnDto getUserLoggingIn(UserLoginDto login);

    RequestResponse addExistingUserToCourse(Long courseId, List<UserEmailsDto> userEmailsDto);

    RequestResponse forgotPassword(UserForgotPassword userForgotPassword);
}
