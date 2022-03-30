package com.example.qsgruppe12.service;

import com.example.qsgruppe12.dto.CourseDto;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {
    CourseDto createCourse(CourseDto courseRegisterDto);


    void deleteCourse(String code, String Semester);
}
