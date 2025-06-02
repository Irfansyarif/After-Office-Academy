package com.tugas2.program.Model.response_model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class addObjectResponse {
    @JsonProperty("myField")
    private String myField;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("data")
    private DataObject data;

    @Data
    public static class DataObject {
        @JsonProperty("year")
        private Integer year;

        @JsonProperty("price")
        private Double price;

        @JsonProperty("cpu_model")
        private String cpuModel;

        @JsonProperty("hard_disk_size")
        private String hardDiskSize;

        @JsonProperty("capacity")
        private String capacity;

        @JsonProperty("screen_size")
        private String screenSize;

        @JsonProperty("color")
        private String color;
    }
}