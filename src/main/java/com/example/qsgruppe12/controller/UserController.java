package com.example.qsgruppe12.controller;

import com.example.qsgruppe12.dto.CourseDto;
import com.example.qsgruppe12.dto.userdtos.*;
import com.example.qsgruppe12.util.RequestResponse;
import jakarta.validation.Valid;
import com.example.qsgruppe12.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users/")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("{userId}/")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto user){
        return userService.updateUser(userId, user);
    }

    @PostMapping("/add/")
    @ResponseStatus(HttpStatus.CREATED)
    public RequestResponse createUsers(@RequestBody List<RegistrationDto> users){
        return userService.createUser(users);
    }

    @PostMapping("/{courseId}/add")
    @ResponseStatus(HttpStatus.CREATED)
    public List<UserDto> createUsers(@PathVariable Long courseId, @RequestBody List<RegistrationDto> userRegisterDto){
        return userService.addUsersForCourse(courseId, userRegisterDto);
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserLoginReturnDto getUser(@Valid @RequestBody LoginDto login){
        return userService.getUserLoggingIn(login);
    }

    @PutMapping("queue/{courseId}/")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getInQueue(@PathVariable Long courseId, @RequestBody QueueDto queueDto, Authentication authentication){
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userService.getInQueue(courseId, queueDto.getEmail());
    }

    @GetMapping("queue/{courseId}/list")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsersInQueue(@RequestBody CourseDto courseDto, @PathVariable String courseId){
        return userService.getUsersInQueue(courseDto.getId());
    }
}
