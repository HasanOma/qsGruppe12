package com.example.qsgruppe12.service.user;

import com.example.qsgruppe12.dto.userdtos.*;
import com.example.qsgruppe12.util.RequestResponse;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface UserService {

    UserDto updateUser(Long id, UserUpdateDto user);

    List<UserDto> getUsersFromCourse(Long courseId);

    RequestResponse createUser(List<UserRegistrationDto> registrations);

    RequestResponse createUser(MultipartFile file) throws IOException, CsvValidationException;

    RequestResponse addUsersForCourse(Long courseId, MultipartFile file) throws IOException, CsvValidationException;

    List<UserDto> addUsersForCourse(Long courseId, List<UserRegistrationDto> registrationDto);

    UserLoginReturnDto getUserLoggingIn(UserLoginDto login);

    RequestResponse addExistingUserToCourse(Long courseId, List<UserEmailsDto> userEmailsDto);

    RequestResponse forgotPassword(UserForgotPassword userForgotPassword);
}
