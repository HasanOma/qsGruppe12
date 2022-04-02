package com.example.qsgruppe12.service.queue;

import com.example.qsgruppe12.dto.QueueDto;
import com.example.qsgruppe12.dto.userdtos.UserDto;
import com.example.qsgruppe12.dto.userdtos.UserGetInQueueDto;
import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.model.UserInQueue;
import com.example.qsgruppe12.repository.CourseRepository;
import com.example.qsgruppe12.repository.QueueRepository;
import com.example.qsgruppe12.repository.UserInQueueRepository;
import com.example.qsgruppe12.repository.UserRepository;
import com.example.qsgruppe12.util.RequestResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for Queue
 */
@Service
public class QueueServiceImpl implements QueueService{

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QueueRepository queueRepository;

    @Autowired
    UserInQueueRepository userInQueueRepository;

    ModelMapper modelMapper = new ModelMapper();

    /**
     *
     * @param courseId
     * @return
     */
    @Override
    public RequestResponse deactivateQueue(Long courseId) {
        //TODO authenticate user permission
        Course course = courseRepository.getById(courseId);
        course.setArchived(false);
        courseRepository.save(course);
        return new RequestResponse("Queue for " + course.getCode() + " is now active");
    }

    /**
     *
     * @param courseId
     * @return
     */
    @Override
    public RequestResponse activateCourseQueue(Long courseId) {
        //TODO authenticate user permission
        Course course = courseRepository.getById(courseId);
        course.setQueueActive(true);
        courseRepository.save(course);
        System.out.println(courseRepository.getById(courseId));
        return new RequestResponse("Queue for " + course.getCode() + " is now active");
    }

    /**
     * Method to get in a queue that is active.
     * @param courseId id of the course that has an active queue.
     * @param queueDto user credentials needed to get in queue.
     * @return returns user information of the user that got in queue.
     */
    @Override
    public UserDto getInQueue(Long courseId, UserGetInQueueDto queueDto) {
        User user = userRepository.getById(queueDto.getUserId());
        if (queueRepository.getByCourseId(courseId).isPresent() && courseRepository.getById(courseId).isQueueActive()){
            if(userRepository.findById(queueDto.getUserId()).isPresent()){
                UserInQueue userInQueue = modelMapper.map(queueDto, UserInQueue.class);
                userInQueue.setFullName(user.firstName + user.getLastName());
                userInQueue.setCourseId(courseId);
                userInQueue.setLocalDate(LocalDate.now());
                userInQueueRepository.save(userInQueue);
            }
        }
        return modelMapper.map(user,UserDto.class);
    }

    /**
     * Retrieves info of users in queue.
     * @param courseId id of the course.
     * @return Returns queue information of all users in an active queue.
     */
    @Override
    public List<QueueDto> getUsersInQueue(Long courseId) {
        if (!courseRepository.getById(courseId).isQueueActive()){
            return null;
        }
        List<UserInQueue> queue = userInQueueRepository.getByCourseId(courseId);
        List<QueueDto> usersInQueue = new ArrayList<>();
        for (UserInQueue userInQueue : queue) {
            usersInQueue.add(modelMapper.map(userInQueue, QueueDto.class));
        }
        return usersInQueue;
    }
}
