package com.example.qsgruppe12.service.queue;

import com.example.qsgruppe12.dto.QueueDto;
import com.example.qsgruppe12.dto.QueueUserIdDto;
import com.example.qsgruppe12.dto.userdtos.UserDto;
import com.example.qsgruppe12.dto.userdtos.UserGetInQueueDto;
import com.example.qsgruppe12.exception.CourseNotFoundException;
import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.model.UserInQueue;
import com.example.qsgruppe12.repository.CourseRepository;
import com.example.qsgruppe12.repository.QueueRepository;
import com.example.qsgruppe12.repository.UserInQueueRepository;
import com.example.qsgruppe12.repository.UserRepository;
import com.example.qsgruppe12.util.RequestResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for Queue
 */
@Slf4j
@NoArgsConstructor
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
     * Method to deactivate a queue
     * @param courseId course id of the queue to close
     * @return returns an acknowledgement
     */
    @Override
    public RequestResponse deactivateQueue(Long courseId) {
        //TODO authenticate user permission
        Course course = courseRepository.getById(courseId);
        course.setQueueActive(false);
        courseRepository.save(course);
        for (int i = 0; i < userInQueueRepository.findAll().size(); i++) {
            userInQueueRepository.deleteByCourseId(courseId);
        }
        System.out.println(courseRepository.getById(courseId));
        return new RequestResponse("closed");
    }

    /**
     * Method to activate a queue
     * @param courseId course id of the queue to close
     * @return returns an acknowledgement
     */
    @Override
    public RequestResponse activateCourseQueue(Long courseId) {
        //TODO authenticate user permission
        Course course = courseRepository.getById(courseId);
        course.setQueueActive(true);
        courseRepository.save(course);
        System.out.println(courseRepository.getById(courseId));
        return new RequestResponse("active");
    }

    /**
     * Method to check if a queue is active
     * @param courseId id of the course
     * @return returns true if queue is active
     */
    @Override
    public boolean isQueueActive(Long courseId){
        Course course = courseRepository.getById(courseId);
        return course.isQueueActive();
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
                userInQueue.setFullName(user.getFirstName() + " " + user.getLastName());
                userInQueue.setCourseId(courseId);
                userInQueue.setLocalDate(LocalTime.now());
                System.out.println(LocalDate.now());
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
    public List<QueueDto> getUsersInQueue(Long courseId) throws CourseNotFoundException {
        if (!courseRepository.getById(courseId).isQueueActive()){
            throw new CourseNotFoundException();
        }
        List<UserInQueue> queue = userInQueueRepository.getByCourseId(courseId);
        List<QueueDto> usersInQueue = new ArrayList<>();
        for (UserInQueue userInQueue : queue) {
            usersInQueue.add(modelMapper.map(userInQueue, QueueDto.class));
            usersInQueue.get(usersInQueue.size()-1).setLocalDate(userInQueue.getLocalDate() + "");
        }
        return usersInQueue;
    }

    /**
     *
     * @param
     * @return
     */
    @Override
    public RequestResponse helpStudent(QueueUserIdDto queueDto, String courseId){
        Course course = courseRepository.getByCode(courseId);
        UserInQueue userInQueue = userInQueueRepository.getByIdAndCourseId(queueDto.getUserId(), course.getId());
        if (!userInQueue.isHelped()){
            userInQueue.setHelped(true);
            userInQueueRepository.save(userInQueue);
            return new RequestResponse("Helped");
        } else {
            userInQueue.setHelped(false);
            userInQueueRepository.save(userInQueue);
            return new RequestResponse("Not helped");
        }
    }

    /**
     *
     * @param studentId
     * @return
     */
    @Override
    public RequestResponse updateStudentInQueue(Long studentId) {
        return null;
    }
}
