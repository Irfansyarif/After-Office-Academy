package com.tugas2.program.Model.response_model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class loginEmployee {
    @JsonProperty("token")
    private String token;

}
