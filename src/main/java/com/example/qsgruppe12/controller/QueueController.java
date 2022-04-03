package com.example.qsgruppe12.controller;

import com.example.qsgruppe12.dto.QueueDto;
import com.example.qsgruppe12.dto.userdtos.UserDto;
import com.example.qsgruppe12.dto.userdtos.UserGetInQueueDto;
import com.example.qsgruppe12.exception.CourseNotFoundException;
import com.example.qsgruppe12.service.SecurityService;
import com.example.qsgruppe12.service.queue.QueueService;
import com.example.qsgruppe12.util.RequestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/queue/{courseId}/")
@Api(tags = "Queue management")
public class QueueController {

    //TODO LOGG

    @Autowired
    QueueService queueService;

    @Autowired
    SecurityService securityService;

    @GetMapping("activate")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Activate queue for a course", response = RequestResponse.class)
    public RequestResponse activateCourse(@PathVariable Long courseId){
        //TODO check if user is TA in that course
        log.debug("[X] Request to activate the queue for course with id = {}",courseId);
        return queueService.activateCourseQueue(courseId);
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get in queue of given course", response = UserDto.class)
    public UserDto getInQueue(@PathVariable Long courseId, @RequestBody UserGetInQueueDto queueDto){
        System.out.println("trying to get in queue");
        log.debug("[X] Request by user with id = {} to get in queue for course with id = {}", queueDto.getUserId(),courseId);
        return queueService.getInQueue(courseId, queueDto);
    }

    @GetMapping("list")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all users in queue info", response = QueueDto.class)
    public List<QueueDto> getUsersInQueue(@PathVariable Long courseId) throws CourseNotFoundException {
        log.debug("[X] Request to get all users in queue for course with id = {}",courseId);
        return queueService.getUsersInQueue(courseId);
    }

    @MessageMapping("list")
    @SendTo("/queue/notify")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all users in queue info", response = QueueDto.class)
    public List<QueueDto> sendUsersInQueue(@PathVariable Long courseId) throws CourseNotFoundException {
        log.debug("[X] Request to get all users in queue for course with id = {}",courseId);
        return queueService.getUsersInQueue(courseId);
    }

    @GetMapping("close")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "Closes a given queue", response = RequestResponse.class)
    public RequestResponse closeQueue(@PathVariable Long courseId){
        //TODO check if user is TA
        log.debug("[X] Request to close a course with id = {}", courseId);
        return queueService.deactivateQueue(courseId);
    }

    @GetMapping("isActive")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Sends true is particular course queue is active", response = boolean.class)
    public boolean isQueueActive(@PathVariable Long courseId){
        return queueService.isQueueActive(courseId);
    }

    //TODO go out of queue update in queue

    @PostMapping("/help")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Sets that the student is being helped", response = RequestResponse.class)
    public RequestResponse helpStudent(@RequestBody QueueDto queueDto, @PathVariable Long courseId){
        log.debug("[X] Request to help user with name = {}", queueDto.getFullName());
        return queueService.helpStudent(queueDto, courseId);
    }

    @PostMapping("{studentId}/update")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Updates User in queue.", response = RequestResponse.class)
    public RequestResponse updateStudentInQueue(@PathVariable Long studentId, @PathVariable Long courseId){
        log.debug("[X] Request to update user with id = {}", studentId);
        return queueService.updateStudentInQueue(studentId);
    }

}
