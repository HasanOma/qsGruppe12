package com.example.qsgruppe12.service.course;


import com.example.qsgruppe12.dto.StudentCourseDto;
import com.example.qsgruppe12.dto.WorkApprovedDto;
import com.example.qsgruppe12.dto.course.CourseDto;
import com.example.qsgruppe12.dto.course.CourseRegisterDto;
import com.example.qsgruppe12.dto.userdtos.UserEmailsDto;
import com.example.qsgruppe12.exception.CourseNotFoundException;
import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.model.Queue;
import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.model.Work;
import com.example.qsgruppe12.model.relationship.User_Course;
import com.example.qsgruppe12.repository.*;
import com.example.qsgruppe12.util.RequestResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

/**
 * Implementation of {@link CourseService}
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    //TODO handle csv file

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private User_CourseRepository userCourseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private UserInQueueRepository userInQueueRepository;

    private ModelMapper modelmapper = new ModelMapper();

    /**
     * Helper method to see if course exists.
     * @param courseRegisterDto Course Dto used to register courses.
     * @return returns true if course already exists.
     */
    private boolean courseExists(CourseDto courseRegisterDto){
        return courseRepository.findByCodeAndSemester(courseRegisterDto.getCode(), courseRegisterDto.getSemester()).isPresent();
    }

    /**
     * Course registration method
     * @param courseRegisterDto Dto user to register a course
     * @param email email to authenticate user
     * @return returns a course Dto object to client
     */
    @Override
    public CourseDto createCourse(CourseRegisterDto courseRegisterDto, String email) throws CourseNotFoundException {
        CourseDto courseDto = modelmapper.map(courseRegisterDto, CourseDto.class);
        if (courseExists(courseDto)) {
            System.out.println("exists");
            throw new CourseNotFoundException("Course Already exists!");
        }
        System.out.println("before mapper");
        Course course = modelmapper.map(courseRegisterDto, Course.class);

        if (course.getSemester().isBlank() || course.getSemester() == null){
            course.setSemester((LocalDate.now().getMonthValue()>=6 ?  "H" : "V") + LocalDate.now().getYear());
        }
        course.setArchived(false);
        course.setQueueActive(false);
        Queue queue = Queue.builder().course(course).build();
        queueRepository.save(queue);
        course.setQueue(queue);
        System.out.println("after mapper");
        return modelmapper.map(courseRepository.save(course), CourseDto.class);
    }

    /**
     * Returns the updated course
     * @param courseId id of the course
     * @param courseDto dto object of course to update
     * @returnreturns updated dto of course
     */
    @Override
    public CourseDto update(Long courseId, CourseDto courseDto) {
        Course course = courseRepository.getById(courseId);
        course.setQueueActive(courseDto.isQueueActive());
        course.setRules(courseDto.getRules());
        return modelmapper.map(courseRepository.save(course),CourseDto.class);
    }

    /**
     * Method to delete a given course
     * @param courseId course id of method to delete
     */
    @Override
    public RequestResponse deleteCourse(Long courseId) {
        if (courseRepository.findById(courseId).isEmpty()){
            return new RequestResponse(new CourseNotFoundException());
        }
        userInQueueRepository.deleteByCourseId(courseId);
        queueRepository.deleteByCourseId(courseId);
        userCourseRepository.deleteByCourse(courseRepository.getById(courseId));
        courseRepository.deleteById(courseId);
        return new RequestResponse("Course with id = " + courseId + " has been deleted.");
    }

    /**
     * Method to check whether students qualify to participate in taking the exam.
     * @param courseId course id to check exam status on.
     * @return returns how many students can take the exam.
     */
    @Override
    public int checkExamStatus(Long courseId) {
        int examReady = 0;
        if (courseRepository.findById(courseId).isEmpty()){
//            throw new CourseException();
        }
        Course course = courseRepository.findById(courseId).get();
        List<String> rules = course.getRules();


        for(int i=0;i<course.getNrOfStudents();i++){
            //gjør om stringen til integers som skal sjekkes
            User_Course user_course = course.getUsers().get(i);
            String work = user_course.getWorkApproved();
            char[] chars = work.toCharArray();
            int[] workInt = new int[chars.length];
            for(int x=0;x<chars.length;x++){
                workInt[x] = Integer.parseInt(String.valueOf(chars[x]));
            }

            for(String rule: rules){
                //splitter opp regelen til integers
                String[] ovingArray = rule.split("[_]");
                int nrOfNeeded = Integer.parseInt(ovingArray[0]);
                int from = Integer.parseInt(ovingArray[1])-1;
                int to = Integer.parseInt(ovingArray[2])-1;

                //sjekker her om arbeidet er gjort, 1 betyr godkjent, 0 betyr ikke godkjent
                for(int k=from;k<=to;k++){
                    if(workInt[k]==1) nrOfNeeded--;
                }

                //dersom antallet gjennomførte øvinger i gruppen er mer enn null, betyr det at
                //studenten ikke har gjennomført riktig antall øvinger i gruppen og kan dermed ikke ta eksamen
                if(nrOfNeeded>0){
                    break;
                }
            }
            //om man kommer seg til dette punktet i koden betyr det at man kan gå opp til eksamen
            //fordi man har alltid gjort like mange eller flere øvingeer enn det som er nødvendig
            user_course.setCanDoExam(true);
            examReady++;
        }
        return examReady;
    }

    /**
     * Returns all variables needed to add student in a course
     * @return returns StudentCourseDto containing list of all courses and list of all students.
     */
    @Override
    public StudentCourseDto getVariables() {
        StudentCourseDto student = new StudentCourseDto();
        student.setEmail(new ArrayList<>());
        student.setCourse(new ArrayList<>());
        student.setCourseIds(new ArrayList<>());

        List<Course> courses = courseRepository.findAll();
        List<User> users = userRepository.findAll();
        for (Course course : courses) {
            student.getCourse().add(course.getName() + " " +
                    course.getCode());
            student.getCourseIds().add(course.getId());
        }
        for (User user : users) {
            student.getEmail().add(user.getEmail());
        }

        return student;
    }

    /**
     * Method to retract all active courses
     * @param emailsDto email DTO.
     * @return returns arraylist of all active courses.
     */
    @Override
    public List<CourseDto> getActiveCourses(String emailsDto) {
        return courses(emailsDto, false);
    }

    /**
     * Method to retract all archived courses.
     * @param emailsDto email DTO.
     * @return returns all archived courses.
     */
    @Override
    public List<CourseDto> getArchivedCourses(String emailsDto) {
        return courses(emailsDto, true);
    }

    /**
     *
     * @param emailsDto
     * @param courseId
     * @return
     */
    @Override
    public WorkApprovedDto getWorkCompleted(UserEmailsDto emailsDto, Long courseId) {
        User user = userRepository.findByEmail(emailsDto.getEmail()).get();
        Course course = courseRepository.getById(courseId);
        User_Course userCourses = userCourseRepository.findByUserAndCourse(user, course);
        List<Work> work = userCourses.getWorkList();
        WorkApprovedDto workApprovedDto = new WorkApprovedDto();
        workApprovedDto.setWork(work);
        workApprovedDto.setCourseName(courseRepository.getById(courseId).getName() + courseRepository.getById(courseId).getName());
        int nrOfWorkApproved = 0;
        for (Work w:work){
            if (w.isCompleted()){
                nrOfWorkApproved++;
            }
        }
        if (0.5 < (Math.floorDiv(work.size(), nrOfWorkApproved))) {
            workApprovedDto.setExamReady(true);
        }
        workApprovedDto.setRules(courseRepository.getById(courseId).getRules());
        return workApprovedDto;
    }

    /**
     * Method to return all courses either archived or active.
     * @param email email of the user.
     * @param activeOrArchived archived or not.
     * @return list of all courses.
     */
    public List<CourseDto> courses(String email, boolean activeOrArchived){
        List<User_Course> userCourses = userCourseRepository.findAll();
        List<Course> courses = new ArrayList<>();
        System.out.println(email);
        User userFromDb = userRepository.findByEmail(email).get();
        for (User_Course userCourse : userCourses) {
            if (userCourse.getUser().getId() == userFromDb.getId()) {
                courses.add(userCourse.getCourse());
            }
        }
        System.out.println(courses.size());
        List<CourseDto> listToReturn = new ArrayList<>();
        for (Course course : courses) {
            if (course.isArchived() == activeOrArchived) {
                listToReturn.add(modelmapper.map(course, CourseDto.class));
            }
        }
        System.out.println();
        return listToReturn;
    }
}
