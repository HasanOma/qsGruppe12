package com.example.qsgruppe12.service.user;

import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * UserDetailsService instance personalized
 */
@Slf4j
@NoArgsConstructor
@Service
public class UserServiceDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * this method retrieves a user by their email
     * @param email email of the user
     * @return returns a {@link UserDetails} object
     */
    @Override
    public UserDetails loadUserByUsername(String email) {
        if(userRepository.findByEmail(email).isEmpty()){
//            throw new IllegalArgumentException();
            System.out.println("user does not exist");
            return null;
        }
        User user = userRepository.findByEmail(email).get();

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
