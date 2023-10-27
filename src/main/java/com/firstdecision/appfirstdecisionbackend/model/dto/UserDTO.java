package com.firstdecision.appfirstdecisionbackend.model.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Name is mandatory.")
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank(message = "E-mail is mandatory.")
    @Email
    private String email;

    @NotBlank(message = "Password is mandatory.")
    @Size(min = 6, max = 20)
    private String password;

}
