package com.example.qsgruppe12.controller;

import com.example.qsgruppe12.dto.StudentCourseDto;
import com.example.qsgruppe12.dto.WorkApprovedDto;
import com.example.qsgruppe12.dto.course.CourseDto;
import com.example.qsgruppe12.dto.course.CourseExamReadyDto;
import com.example.qsgruppe12.dto.course.CourseRegisterDto;
import com.example.qsgruppe12.dto.userdtos.UserEmailsDto;
import com.example.qsgruppe12.exception.CourseNotFoundException;
import com.example.qsgruppe12.service.course.CourseService;
import com.example.qsgruppe12.util.RequestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/courses/")
@Api(tags = "Course management")
public class CourseController {

    //TODO add authorization checks on calls

    @Autowired
    CourseService courseService;

    @GetMapping("student_courses")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Returns variables client can choose from", response = StudentCourseDto.class)
    public StudentCourseDto getVariables(){
        log.debug("[X] Request to get all emails of users and all courses ");
        return courseService.getVariables();
    }

    @PostMapping("active")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Returns all active courses the client participates in", response = CourseDto.class)
    public List<CourseDto> getActiveCourses(@RequestBody UserEmailsDto emailsDto){
        log.debug("[X] Request to get all active courses of users with email {}", emailsDto.getEmail());
        List<CourseDto> courses = courseService.getActiveCourses(emailsDto);
        System.out.println(courses.toString());
        return courses;
    }

    @PostMapping("archived")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Returns all archived courses the client has participated in", response = CourseDto.class)
    public List<CourseDto> getArchivedCourses(@RequestBody UserEmailsDto emailsDto){
        log.debug("[X] Request to get all archived courses of users with email {}", emailsDto.getEmail());
        return courseService.getArchivedCourses(emailsDto);
    }

    @PostMapping("{courseId}/work")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Returns all archived courses the client has participated in", response = CourseDto.class)
    public WorkApprovedDto getWorkCompleted(@RequestBody UserEmailsDto emailsDto, @PathVariable Long courseId){
        log.debug("[X] Request to get all work in the course with id = {} of users with email {}", courseId ,emailsDto.getEmail());
        return courseService.getWorkCompleted(emailsDto, courseId);
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Creates a course", response = CourseDto.class)
    public CourseDto createCourse(Authentication authentication, @RequestBody CourseRegisterDto courseDto)
            throws CourseNotFoundException {
        log.debug("[X] Request to course course with code = {}", courseDto.getCode());
        return courseService.createCourse(courseDto, authentication.getName());
    }

    @DeleteMapping("{courseId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "Deletes a course", response = CourseDto.class)
    public RequestResponse deleteCourse(Authentication authentication, @PathVariable Long courseId){
        log.debug("[X] Request to delete a course with id = {}",courseId);
        return courseService.deleteCourse(courseId);
    }


    @PutMapping("{courseId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a course's info", response = CourseDto.class)
    public CourseDto updateCourse(Authentication authentication, @PathVariable Long courseId,
                                  @RequestBody CourseDto courseDto){
        log.debug("[X] Request to update a course with id = {}",courseId);
        return courseService.update(courseId, courseDto);
    }

    @GetMapping("activate")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Checks the exam status of the course", response = CourseExamReadyDto.class)
    public List<CourseExamReadyDto> checkExamStatus(@RequestBody List<Long> courseIds){
        log.debug("[X] Request to check exam status for courses");
        List<CourseExamReadyDto> courseRegisterDtos = new ArrayList<>();
        for (Long courseId : courseIds) {
            CourseExamReadyDto.builder()
                    .examReady(courseService.checkExamStatus(courseId)).id(courseId).build();
        }
        return courseRegisterDtos;
    }
}
