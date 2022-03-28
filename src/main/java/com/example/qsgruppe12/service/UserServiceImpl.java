package com.example.qsgruppe12.service;

import com.example.qsgruppe12.dto.CourseDto;
import com.example.qsgruppe12.dto.userdtos.RegistrationDto;
import com.example.qsgruppe12.dto.userdtos.UserDto;
import com.example.qsgruppe12.model.Student;
import com.example.qsgruppe12.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    //TODO email sent to users created

    @Autowired
    private StudentRepository studentRepository;

    private BCryptPasswordEncoder cryptPasswordEncoder;

    private ModelMapper modelMapper;

    @Override
    public UserDto updateUser(Long id, UserDto user) {
        return null;
    }

    @Override
    public List<UserDto> getUsersFromCourse(Long id, CourseDto course) {
        return null;
    }

    @Override
    public List<UserDto> addUsersForCourse(Long courseId, List<RegistrationDto> registrationDto) {
        //TODO if course does not exist or if user exists throw exception
        List<UserDto> savedStudents = new ArrayList<>();
        for (RegistrationDto dto : registrationDto) {
            Student student = modelMapper.map(dto, Student.class);
            student.setPassword(cryptPasswordEncoder.encode(dto.getPassword()));
            UserDto studentAdded = modelMapper.map(studentRepository.save(student), UserDto.class);
            savedStudents.add(studentAdded);
        }
        return savedStudents;
    }

    @Override
    public void addTAsForCourse(Long courseId, List<RegistrationDto> registrationDto) {

    }
}
