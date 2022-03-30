package com.example.qsgruppe12.controller;

import com.example.qsgruppe12.dto.CourseDto;
import com.example.qsgruppe12.dto.userdtos.UserDto;
import com.example.qsgruppe12.dto.userdtos.UserLoginDto;
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

    @GetMapping("myInfo/")
    @ResponseStatus(HttpStatus.OK)
    public UserLoginDto getUser(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userService.getUserLoggingIn(userDetails.getUsername());
    }

    @GetMapping("queue/{courseId}/")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getInQueue(@PathVariable Long courseId, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userService.getInQueue(courseId, userDetails.getUsername());
    }

    @GetMapping("queue/{courseId}/list")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsersInQueue(@RequestBody CourseDto courseDto){
        return userService.getUsersInQueue(courseDto.getId());
    }
}
