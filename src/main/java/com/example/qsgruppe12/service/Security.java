package com.example.qsgruppe12.service;

import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.repository.CourseRepository;
import com.example.qsgruppe12.repository.UserRepository;
import com.example.qsgruppe12.repository.User_CourseRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class Security {

    UserRepository userRepository;

    CourseRepository courseRepository;

    User_CourseRepository userCourseRepository;

    private User getUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails details = (UserDetails) auth.getPrincipal();
        String email = details.getUsername();
        return userRepository.findByEmail(email).get();
    }

    private boolean isAdmin(){
        return false;
    }

    private boolean isTA(){
        return false;
    }
}
