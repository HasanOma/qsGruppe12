package com.example.qsgruppe12.service.queue;

import com.example.qsgruppe12.dto.QueueDto;
import com.example.qsgruppe12.dto.userdtos.UserDto;
import com.example.qsgruppe12.dto.userdtos.UserGetInQueueDto;
import com.example.qsgruppe12.exception.CourseNotFoundException;
import com.example.qsgruppe12.util.RequestResponse;

import java.util.List;

public interface QueueService {

    RequestResponse activateCourseQueue(Long courseId);

    RequestResponse deactivateQueue(Long courseId);

    UserDto getInQueue(Long courseId, UserGetInQueueDto queueDto);

    List<QueueDto> getUsersInQueue(Long courseId) throws CourseNotFoundException;

    boolean isQueueActive(Long courseId);

    RequestResponse helpStudent(QueueDto queueDto, Long courseId);

    RequestResponse updateStudentInQueue(Long studentId);
}
