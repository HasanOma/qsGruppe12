package com.example.qsgruppe12.dto.userdtos;

import com.example.qsgruppe12.dto.CourseDto;
import jakarta.validation.constraints.Email;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginReturnDto {

    @NotNull
    private Long id;
    @NotNull
    @NotEmpty
    private String firstName;
    @NotNull
    @NotEmpty
    private String lastName;
    @NotNull
    @NotEmpty
    private String email;
    @Email
    private String altEmail;

    private String userRoleName;

    @NotNull
    private List<CourseDto>  courses;

}