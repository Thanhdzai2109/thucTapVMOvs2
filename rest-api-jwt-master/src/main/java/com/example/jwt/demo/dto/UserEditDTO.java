package com.example.jwt.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserEditDTO {
    private int user_id;
    @NotNull
    @Size(min = 2, message = "Username should be has more than 2 characters")
    @Pattern(regexp = "^([A-Za-z0-9_\\s])*$", message = "Please input valid username")
    private String user_name;
    @NotNull(message = "Email required. Please provide an email")
    @Email(message = "Please input valid email address")
    private String user_email;
    private int height;

    private int weight;

    private int gender;

    private int age;
}
