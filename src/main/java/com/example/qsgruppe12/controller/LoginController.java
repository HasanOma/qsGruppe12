package com.example.qsgruppe12.controller;

import com.example.qsgruppe12.dto.JWTRequest;
import com.example.qsgruppe12.dto.JWTResponse;
import com.example.qsgruppe12.dto.userdtos.UserLoginDto;
import com.example.qsgruppe12.dto.userdtos.UserLoginReturnDto;
import com.example.qsgruppe12.repository.UserRepository;
import com.example.qsgruppe12.service.user.UserService;
import com.example.qsgruppe12.service.user.UserServiceDetails;
import com.example.qsgruppe12.util.JWTUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private UserService userService;

//    private BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserServiceDetails userServiceDetails;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserLoginReturnDto authenticate(@Valid @RequestBody JWTRequest jwtRequest) throws Exception{
        UserLoginReturnDto user;
        try {
//            String password = cryptPasswordEncoder.encode();
            user = userService.getUserLoggingIn(modelMapper.map(jwtRequest, UserLoginDto.class));

        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userServiceDetails.loadUserByUsername(jwtRequest.getEmail());

        final String token =
                jwtUtil.generateToken(userDetails);
        user.setJwtResponse(new JWTResponse(token));
        return  user;
    }
}
