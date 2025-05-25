package com.tugas2.program.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class loginModel {
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;
}
