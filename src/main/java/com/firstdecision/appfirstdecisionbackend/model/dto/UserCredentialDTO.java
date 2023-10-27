package com.firstdecision.appfirstdecisionbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentialDTO {

    @NotBlank(message = "E-mail is mandatory.")
    @Email
    private String email;

    @NotBlank(message = "Password is mandatory.")
    @Size(min = 6, max = 20)
    private String password;

}
