package com.example.qsgruppe12.controller;

import com.example.qsgruppe12.dto.CourseDto;
import com.example.qsgruppe12.dto.userdtos.LoginDto;
import com.example.qsgruppe12.dto.userdtos.UserDto;
<<<<<<< HEAD
import com.example.qsgruppe12.dto.userdtos.UserLoginReturnDto;
import com.example.qsgruppe12.service.UserService;
import jakarta.validation.Valid;
=======
import com.example.qsgruppe12.dto.userdtos.UserLoginDto;
import com.example.qsgruppe12.service.user.UserService;
>>>>>>> 47763c13384e0d635d62c16cd0006aec5e3d58a0
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

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserLoginReturnDto getUser(@Valid @RequestBody LoginDto login){
        return userService.getUserLoggingIn(login);
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
