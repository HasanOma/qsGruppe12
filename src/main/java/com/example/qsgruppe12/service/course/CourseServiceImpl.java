package com.example.qsgruppe12.service.course;


import com.example.qsgruppe12.dto.StudentCourseDto;
import com.example.qsgruppe12.dto.course.CourseDto;
import com.example.qsgruppe12.dto.course.CourseRegisterDto;
import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.model.Queue;
import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.model.relationship.User_Course;
import com.example.qsgruppe12.repository.CourseRepository;
import com.example.qsgruppe12.repository.QueueRepository;
import com.example.qsgruppe12.repository.UserRepository;
import com.example.qsgruppe12.repository.User_CourseRepository;
import com.example.qsgruppe12.util.RequestResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Implementation of {@link CourseService}
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private User_CourseRepository userCourseRepository;

    @Autowired
    private QueueRepository queueRepository;

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
     * @param email
     * @return returns a course Dto object to client
     */
    @Override
    public CourseDto createCourse(CourseRegisterDto courseRegisterDto, String email) {
        CourseDto courseDto = modelmapper.map(courseRegisterDto, CourseDto.class);
        if (courseExists(courseDto)) {
//            throw new CourseExistsException();
            return null;
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

    @Override
    public CourseDto update(Long courseId, CourseDto courseDto) {
        Course course = courseRepository.findById(courseId).get();
        course.setQueueActive(courseDto.isQueueActive());
        course.setRules(courseDto.getRules());
        return modelmapper.map(courseRepository.save(course),CourseDto.class);
    }

    @Override
    public void deleteCourse(Long courseId) {
        if (courseRepository.findById(courseId).isEmpty()){
//            throw new CourseException();
        }
        courseRepository.deleteById(courseId);
    }

    @Override
    public int checkExamStatus(Long courseId) {
        int examReady = 0;
        if (courseRepository.findById(courseId).isEmpty()){
//            throw new CourseException();
        }
        Course course = courseRepository.findById(courseId).get();
        String rules = course.getRules();

        String[] ruleArray =  rules.split("[.]");

        for(int i=0;i<course.getNrOfStudents();i++){
            //gjør om stringen til integers som skal sjekkes
            User_Course user_course = course.getUsers().get(i);
            String work = user_course.getWorkApproved();
            char[] chars = work.toCharArray();
            int[] workInt = new int[chars.length];
            for(int x=0;x<chars.length;x++){
                workInt[x] = Integer.parseInt(String.valueOf(chars[x]));
            }

            for(int j = 0;j<ruleArray.length;j++){
                //splitter opp regelen til integers
                String[] ovingArray = ruleArray[j].split("[_]");
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

}
