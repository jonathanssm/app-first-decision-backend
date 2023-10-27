package com.firstdecision.appfirstdecisionbackend.model.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserFilter {

    @Size(max = 50)
    private String name;

    @Email
    private String email;

}
