package com.dg.ums.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class DGUser {

    private int id;

    @Size(min = 3, message = "at least 3 characters required for firstname.")
    private String firstName;

    @Size(min = 1, message = "at least 1 character required for lastname.")
    private String lastName;

    @Past(message = "date of birth should be on past.")
    private LocalDate dob;

    @Email
    private String email;

    @NotBlank(message = "Username should not be empty")
    @Size(min = 6, max = 16, message = "username should have at least 5 characters long.")
    private String username;

    private byte[] profilePic;
}
