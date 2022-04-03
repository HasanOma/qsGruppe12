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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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
    @ApiOperation(value = "Update a users info", response = RequestResponse.class)
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserUpdateDto user){
        log.debug("[X] Request to update a user with id = {}",userId);
        return userService.updateUser(userId, user);
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Adds new users to the database", response = RequestResponse.class)
    public RequestResponse createUsers(@RequestBody List<UserRegistrationDto> users){
        log.debug("[X] Request to create users");
        return userService.createUser(users);
    }

    @RequestMapping(value = "add/file" , method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Adds new users to the database from a file", response = RequestResponse.class)
    public RequestResponse createUsers(@RequestPart("file") MultipartFile file) throws IOException {
        log.debug("[X] Request to create users from a file");

        return userService.createUser(file);
    }

    @PostMapping("{courseId}/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Adds new users to a course", response = RequestResponse.class)
    public List<UserDto> createUsers(@PathVariable Long courseId,
                                     @RequestBody List<UserRegistrationDto> userRegisterDto){
        log.debug("[X] Request to create users for course with id = {}",courseId);
        return userService.addUsersForCourse(courseId, userRegisterDto);
    }

    @RequestMapping(value = "{courseId}/add/file" , method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Adds new users to the database", response = RequestResponse.class)
    public RequestResponse addUsersToCourse(@PathVariable Long courseId, @RequestPart("file") MultipartFile file)
            throws IOException {
        log.debug("[X] Request to create users from file for course with id = {}",courseId);

        return userService.addUsersForCourse(courseId, file);
    }

    @PutMapping("/{courseId}/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "Adds existing users to a course", response = RequestResponse.class)
    public RequestResponse addExistingUserToCourse(@PathVariable Long courseId,
                                                   @RequestBody List<UserEmailsDto> userEmailsDto){
        log.debug("[X] Request to add existing users for course with id = {}",courseId);
        return userService.addExistingUserToCourse(courseId, userEmailsDto);
    }

}
