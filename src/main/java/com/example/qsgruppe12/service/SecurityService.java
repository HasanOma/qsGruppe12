package com.example.qsgruppe12.service;

import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.repository.CourseRepository;
import com.example.qsgruppe12.repository.UserRepository;
import com.example.qsgruppe12.repository.User_CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Service class for security checks
 */
@AllArgsConstructor
@Service
public class SecurityService {

    UserRepository userRepository;

    CourseRepository courseRepository;

    User_CourseRepository userCourseRepository;

    /**
     * Method to get user trying to access endpoints from the database.
     * @return method to get user requesting endpoints.
     */
    private User getUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails details = (UserDetails) auth.getPrincipal();
        String email = details.getUsername();
        return userRepository.findByEmail(email).get();
    }

    /**
     * Helper method to check if user is Admin.
     * @return returns true if user is admin.
     */
    private boolean isAdmin(){
        return false;
    }

    /**
     * Helper method to check if user is TA.
     * @return returns true if user is TA.
     */
    private boolean isTA(){
        return false;
    }

    /**
     * Method to check if user has authority to edit queue instance.
     */
    private boolean canEditQueueCheck(){
        return false;
    }
}
