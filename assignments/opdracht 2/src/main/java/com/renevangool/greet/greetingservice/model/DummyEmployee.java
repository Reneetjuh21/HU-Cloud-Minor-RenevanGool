package com.renevangool.greet.greetingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown=true)
public class DummyEmployee implements Serializable {
    @JsonProperty("id")
    private long id;

    @JsonProperty("employee_name")
    private String name;

    @JsonProperty("employee_salary")
    private String salary;

    @JsonProperty("employee_age")
    private int age;

    @JsonProperty("profile_image")
    private String image;
}
