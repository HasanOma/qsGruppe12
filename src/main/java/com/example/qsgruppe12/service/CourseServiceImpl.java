package com.example.qsgruppe12.service;


import com.example.qsgruppe12.dto.CourseDto;
import com.example.qsgruppe12.dto.CourseRegisterDto;
import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.repository.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    private ModelMapper modelmapper = new ModelMapper();

    private boolean courseExists(CourseDto courseRegisterDto){
        boolean exists = false;
        if (courseRepository.findByName(courseRegisterDto.getName()).isPresent()){
            if (courseRepository.findByName(courseRegisterDto.getName())
                    .get().getSemester().equalsIgnoreCase(courseRegisterDto.getSemester())){
                exists = true;
            }
        }
        return exists;
    }

    @Override
    public CourseDto createCourse(CourseDto courseRegisterDto) {
        if (courseExists(courseRegisterDto)) {
//            throw new CourseExistsException();
            return null;
        }
        System.out.println("before mapper");
        Course course = modelmapper.map(courseRegisterDto, Course.class);
        System.out.println("after mapper");
        return modelmapper.map(courseRepository.save(course), CourseDto.class);
    }
}
