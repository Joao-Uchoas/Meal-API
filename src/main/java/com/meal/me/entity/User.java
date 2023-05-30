package com.meal.me.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class User  {
    private String activityLevel;
    private String basalMetabolicRate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthDate;
    private String goal;
    private Integer height;
    private Meals[] meals;
    private String name;
    private String sex;
    private String wight;
}