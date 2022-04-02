package com.example.qsgruppe12.controller;

import com.example.qsgruppe12.dto.QueueDto;
import com.example.qsgruppe12.dto.userdtos.UserDto;
import com.example.qsgruppe12.dto.userdtos.UserGetInQueueDto;
import com.example.qsgruppe12.service.SecurityService;
import com.example.qsgruppe12.service.course.CourseService;
import com.example.qsgruppe12.service.user.UserService;
import com.example.qsgruppe12.util.RequestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/queue/{courseId}/")
@Api(tags = "Queue management")
public class QueueController {

    //TODO LOGG

    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @Autowired
    CourseService courseService;

    @PostMapping("/activate")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Activate queue for a course")
    public RequestResponse activateCourse(@PathVariable Long courseId){
        //TODO check if user is TA in that course
        return courseService.activateCourseQueue(courseId);
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get in queue of given course")
    public UserDto getInQueue(@PathVariable Long courseId, @RequestBody UserGetInQueueDto queueDto){
        System.out.println("trying to get in queue");
        return userService.getInQueue(courseId, queueDto);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all users in queue info")
    public List<QueueDto> getUsersInQueue(@PathVariable Long courseId){
        return userService.getUsersInQueue(courseId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "Closes a given queue")
    public List<RequestResponse> closeQueue(@PathVariable Long courseId){
        //TODO check if user is TA
        return null;
    }

    //TODO go out of queue update in queue

}
