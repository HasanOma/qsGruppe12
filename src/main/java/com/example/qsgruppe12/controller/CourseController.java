package com.example.qsgruppe12.controller;

import com.example.qsgruppe12.dto.CourseDto;
import com.example.qsgruppe12.dto.CourseRegisterDto;
import com.example.qsgruppe12.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

}
