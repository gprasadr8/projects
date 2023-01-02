package com.dg.ums.entities;

import com.dg.ums.model.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "dg_ums_user")
@Setter
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private LocalDate dob;

    @Email(message = "Invalid email Id")
    private String email;

    @NotBlank(message = "Username should not be empty")
    @Size(min = 8, max = 16)
    private String username;

    private byte[] profilePic;

}
