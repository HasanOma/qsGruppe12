package com.example.qsgruppe12.controller;

import com.example.qsgruppe12.dto.QueueDto;
import com.example.qsgruppe12.dto.userdtos.*;
import com.example.qsgruppe12.util.RequestResponse;
import jakarta.validation.Valid;
import com.example.qsgruppe12.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserUpdateDto user){
        return userService.updateUser(userId, user);
    }

    @PostMapping("/add/")
    @ResponseStatus(HttpStatus.CREATED)
    public RequestResponse createUsers(@RequestBody List<UserRegistrationDto> users){
        return userService.createUser(users);
    }

    @PostMapping("/{courseId}/add")
    @ResponseStatus(HttpStatus.CREATED)
    public List<UserDto> createUsers(@PathVariable Long courseId, @RequestBody List<UserRegistrationDto> userRegisterDto){
        return userService.addUsersForCourse(courseId, userRegisterDto);
    }

    @PutMapping("/{courseId}/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public RequestResponse addExistingUserToCourse(@PathVariable Long courseId, @RequestBody List<UserEmailsDto> userEmailsDto){
        return userService.addExistingUserToCourse(courseId, userEmailsDto);
    }

    @PutMapping("queue/{courseId}/")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getInQueue(@PathVariable Long courseId, @RequestBody UserGetInQueueDto queueDto){
        System.out.println("trying to get in queue");
        return userService.getInQueue(courseId, queueDto);
    }

    @GetMapping("queue/{courseId}/list")
    @ResponseStatus(HttpStatus.OK)
    public List<QueueDto> getUsersInQueue(@PathVariable Long courseId){
        return userService.getUsersInQueue(courseId);
    }

    //TODO go out of queue update in queue
}
