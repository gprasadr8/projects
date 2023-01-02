package com.dg.ums.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DGUser {

    private int id;

    private String firstName;

    private String lastName;

    private LocalDate dob;

    private String email;

    private String username;

    private byte[] profilePic;
}
