package com.example.qsgruppe12.dto.userdtos;

import com.example.qsgruppe12.dto.CourseDto;
import com.example.qsgruppe12.dto.CourseRegisterDto;
import com.example.qsgruppe12.model.Course;
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
public class UserDto {

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
    @Email
    private String email;
    @Email
    public String altEmail;

    private String userRoleName;

    @NotNull
    private List<CourseRegisterDto>  courses;

}
