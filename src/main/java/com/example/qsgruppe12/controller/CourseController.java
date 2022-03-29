package com.example.qsgruppe12.controller;

import com.example.qsgruppe12.dto.CourseDto;
import com.example.qsgruppe12.dto.CourseRegisterDto;
import com.example.qsgruppe12.service.CourseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/courses/")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDto createCourse(@Valid @RequestBody CourseDto courseDto){
        System.out.println("here");
        return courseService.createCourse(courseDto);
    }
}
