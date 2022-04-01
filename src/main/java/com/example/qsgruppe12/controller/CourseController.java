package com.example.qsgruppe12.controller;

import com.example.qsgruppe12.dto.course.CourseDto;
import com.example.qsgruppe12.dto.course.CourseExamReadyDto;
import com.example.qsgruppe12.dto.course.CourseRegisterDto;
import com.example.qsgruppe12.service.course.CourseService;
import com.example.qsgruppe12.util.RequestResponse;
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
public class CourseController {

    @Autowired
    CourseService courseService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDto createCourse(Authentication authentication, @RequestBody CourseRegisterDto courseDto){
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("here");
        return courseService.createCourse(courseDto, "min email");
    }

    @DeleteMapping("{courseId}/")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCourse(Authentication authentication, @PathVariable Long courseId){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        courseService.deleteCourse(courseId);
        return "Course has been deleted.";
    }

    @PutMapping("{courseId}/")
    public CourseDto updateCourse(Authentication authentication, @PathVariable Long courseId,
                                  @RequestBody CourseDto courseDto){
        return courseService.update(courseId, courseDto);
    }

    @GetMapping("{courseId}/activate")
    public RequestResponse activateCourse(@PathVariable Long courseId){
        return courseService.activateCourseQueue(courseId);
    }

    @GetMapping("/activate")
    public List<CourseExamReadyDto> checkExamStatus(@RequestBody List<Long> courseIds){
        List<CourseExamReadyDto> courseRegisterDtos = new ArrayList<>();
        for (Long courseId : courseIds) {
            CourseExamReadyDto.builder()
                    .examReady(courseService.checkExamStatus(courseId)).id(courseId).build();
        }
        return courseRegisterDtos;
    }
}
