package com.example.qsgruppe12.controller;

import com.example.qsgruppe12.dto.JWTRequest;
import com.example.qsgruppe12.dto.JWTResponse;
import com.example.qsgruppe12.dto.course.CourseDto;
import com.example.qsgruppe12.dto.userdtos.UserForgotPassword;
import com.example.qsgruppe12.dto.userdtos.UserLoginDto;
import com.example.qsgruppe12.dto.userdtos.UserLoginReturnDto;
import com.example.qsgruppe12.repository.UserRepository;
import com.example.qsgruppe12.service.user.UserService;
import com.example.qsgruppe12.service.user.UserServiceDetails;
import com.example.qsgruppe12.util.JWTUtil;
import com.example.qsgruppe12.util.RequestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "Login management")
public class LoginController {

    //TODO LOGG

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceDetails userServiceDetails;

    /**
     * Login endpoint to veryfy that user exists in the database.
     * @param jwtRequest requestbody containing user login credentials.
     * @return returns information nessasery for the state to keep track on client side.
     * @throws Exception throws a bad credentials exception.
     */
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Authenticates, logs in user and createsJWT token", response = UserLoginReturnDto.class)
    public UserLoginReturnDto authenticate(@Valid @RequestBody JWTRequest jwtRequest) throws Exception{
        UserLoginReturnDto user;
        System.out.println(jwtRequest.getEmail() + " " + jwtRequest.getPassword());

        try {

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

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "Sends user a new Password to their email", response = RequestResponse.class)
    public RequestResponse forgotPassword(@Valid @RequestBody UserForgotPassword userForgotPassword){
        return userService.forgotPassword(userForgotPassword);
    }
}
