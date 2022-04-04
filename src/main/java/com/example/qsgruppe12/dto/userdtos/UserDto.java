package com.example.qsgruppe12.dto.userdtos;

import com.example.qsgruppe12.dto.course.CourseRegisterDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Data transfer object sent back to user when login is successful")
public class UserDto {

    @NotNull
    @ApiModelProperty(notes = "Id of the user how they is stored in the database")
    private Long id;
    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "First name of the user")
    private String firstName;
    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "Last name of the user")
    private String lastName;
    @NotNull
    @NotEmpty
    @Email
    @ApiModelProperty(notes = "Email of the user")
    private String email;
    @Email
    @ApiModelProperty(notes = "Alternative email of the user")
    public String altEmail;

    @ApiModelProperty(notes = "Role of the user")
    private String userRoleName;

    @NotNull
    @ApiModelProperty(notes = "List of course in DTO form that the user has a relationship with")
    private List<CourseRegisterDto>  courses;

}
