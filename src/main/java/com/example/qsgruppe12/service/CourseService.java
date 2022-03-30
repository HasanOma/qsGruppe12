package com.example.qsgruppe12.service;

import com.example.qsgruppe12.dto.CourseDto;
import com.example.qsgruppe12.model.Course;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {
    CourseDto createCourse(CourseDto courseRegisterDto);

    CourseDto update();

    void deleteCourse(Long courseId);

    void checkExamStatus(Long courseId);
}
