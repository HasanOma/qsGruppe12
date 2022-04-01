package com.example.qsgruppe12.service.course;

import com.example.qsgruppe12.dto.CourseDto;
import com.example.qsgruppe12.dto.CourseRegisterDto;
import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.util.RequestResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public interface CourseService {
    CourseDto createCourse(CourseRegisterDto courseRegisterDto, String email);

    CourseDto update(Long courseId, CourseDto courseDto);

    void deleteCourse(Long courseId);

    void checkExamStatus(Long courseId);

    RequestResponse activateCourseQueue(Long courseId);
}
