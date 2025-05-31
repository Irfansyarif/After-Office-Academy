package com.tugas2.program.Model.response_model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class addObjectResponse {

    @JsonProperty("name")
    private Integer name;

    @JsonProperty("year")
    private String year;

    @JsonProperty("price")
    private String price;

    @JsonProperty("cpu_model")
    private String cpuModel;

    @JsonProperty("hard_disk_size")
    private String HardDiskSize;

    @JsonProperty("capactity")
    private String capacity;

    @JsonProperty("screen_size")
    private String screenSize;

    @JsonProperty("color")
    private String color;
}