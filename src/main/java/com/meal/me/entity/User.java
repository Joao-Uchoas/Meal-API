package com.meal.me.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class User  {
    private String documentId;
    private String activityLevel;
    private String basalMetabolicRate;
    private String birthDate;
    private Map<String, Object> goals;
    private Integer height;
    private Map<String, Object> meals;
    private String name;
    private String sex;
    private Integer weight;
}