package com.example.qsgruppe12.service.course;

import com.example.qsgruppe12.dto.StudentCourseDto;
import com.example.qsgruppe12.dto.WorkApprovedDto;
import com.example.qsgruppe12.dto.course.CourseDto;
import com.example.qsgruppe12.dto.course.CourseRegisterDto;
import com.example.qsgruppe12.dto.userdtos.UserEmailsDto;
import com.example.qsgruppe12.exception.CourseNotFoundException;
import com.example.qsgruppe12.util.RequestResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    CourseDto createCourse(CourseRegisterDto courseRegisterDto, String email) throws CourseNotFoundException;

    CourseDto update(Long courseId, CourseDto courseDto);

    RequestResponse deleteCourse(Long courseId);

    int checkExamStatus(Long courseId);

    StudentCourseDto getVariables();

    List<CourseDto> getActiveCourses(UserEmailsDto emailsDto);

    List<CourseDto> getArchivedCourses(UserEmailsDto emailsDto);

    WorkApprovedDto getWorkCompleted(UserEmailsDto emailsDto, Long courseId);

}
