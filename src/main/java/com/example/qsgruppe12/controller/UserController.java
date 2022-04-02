package com.example.qsgruppe12.controller;

import com.example.qsgruppe12.dto.QueueDto;
import com.example.qsgruppe12.dto.userdtos.*;
import com.example.qsgruppe12.service.SecurityService;
import com.example.qsgruppe12.util.RequestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import com.example.qsgruppe12.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users/")
@Api(tags = "User management")
public class UserController {

    //TODO Run security check on users
    //TODO LOGG

    @Autowired
    private UserService userService;

    @Autowired
    SecurityService securityService;

    @PutMapping("{userId}/")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a users info")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserUpdateDto user){
        return userService.updateUser(userId, user);
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Adds new users to the database")
    public RequestResponse createUsers(@RequestBody List<UserRegistrationDto> users){
        return userService.createUser(users);
    }

    @PostMapping("{courseId}/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Adds new users to a course")
    public List<UserDto> createUsers(@PathVariable Long courseId, @RequestBody List<UserRegistrationDto> userRegisterDto){
        return userService.addUsersForCourse(courseId, userRegisterDto);
    }

    @PutMapping("/{courseId}/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "Adds existing users to a course")
    public RequestResponse addExistingUserToCourse(@PathVariable Long courseId, @RequestBody List<UserEmailsDto> userEmailsDto){
        return userService.addExistingUserToCourse(courseId, userEmailsDto);
    }

}
