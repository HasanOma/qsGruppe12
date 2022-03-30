package com.example.qsgruppe12.service;


import com.example.qsgruppe12.dto.CourseDto;
import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.model.relationship.User_Course;
import com.example.qsgruppe12.repository.CourseRepository;
import com.example.qsgruppe12.repository.User_CourseRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private User_CourseRepository userCourseRepository;

    private ModelMapper modelmapper = new ModelMapper();

    private boolean courseExists(CourseDto courseRegisterDto){
        return courseRepository.findByCodeAndSemester(courseRegisterDto.getCode(), courseRegisterDto.getSemester()).isPresent();
    }

    @Override
    public CourseDto createCourse(CourseDto courseRegisterDto) {
        if (courseExists(courseRegisterDto)) {
//            throw new CourseExistsException();
            return null;
        }
        System.out.println("before mapper");
        Course course = modelmapper.map(courseRegisterDto, Course.class);
        course.setSemester((LocalDate.now().getMonthValue()>=6 ?  "H" : "V") + LocalDate.now().getYear());
        course.setArchived(false);
        course.setQueueActive(false);
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
    public void checkExamStatus(Long courseId) {
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
                    return;
                }
            }
            //om man kommer seg til dette punktet i koden betyr det at man kan gå opp til eksamen
            //fordi man har alltid gjort like mange eller flere øvingeer enn det som er nødvendig
            user_course.setCanDoExam(true);
        }


    }
}
