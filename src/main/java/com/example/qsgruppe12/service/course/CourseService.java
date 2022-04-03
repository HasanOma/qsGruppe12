package com.example.qsgruppe12.service.course;

import com.example.qsgruppe12.dto.StudentCourseDto;
import com.example.qsgruppe12.dto.course.CourseDto;
import com.example.qsgruppe12.dto.course.CourseRegisterDto;
import com.example.qsgruppe12.exception.CourseNotFoundException;
import com.example.qsgruppe12.util.RequestResponse;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {
    CourseDto createCourse(CourseRegisterDto courseRegisterDto, String email) throws CourseNotFoundException;

    CourseDto update(Long courseId, CourseDto courseDto);

    RequestResponse deleteCourse(Long courseId);

    int checkExamStatus(Long courseId);

    StudentCourseDto getVariables();

}
