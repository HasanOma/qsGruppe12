package com.example.qsgruppe12.service;


import com.example.qsgruppe12.dto.CourseDto;
import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.repository.CourseRepository;
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
        System.out.println("after mapper");
        return modelmapper.map(courseRepository.save(course), CourseDto.class);
    }

    @Override
    public CourseDto update() {
        return null;
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
//        if (courseRepository.findById(courseId).isEmpty()){
////            throw new CourseException();
//        }
//        Course course = courseRepository.findById(courseId).get();
//        String rules = course.getRules();
//        //split rules på hver 9ende karakter
//        String twoRules = "3_1_5.1_6_8";
//        String[] ruleArray =  rules.split("[.]");
//        boolean[] ruleCheck = new boolean[ruleArray.length];
//
//        for(int i = 0;i<ruleArray.length;i++){
//            String[] ovingArray = ruleArray[i].split("[_]");
//            int nr = Integer.parseInt(ovingArray[0]);
//            int from = Integer.parseInt(ovingArray[1]);
//            int to = Integer.parseInt(ovingArray[2]);
//        }
//
    }
}
