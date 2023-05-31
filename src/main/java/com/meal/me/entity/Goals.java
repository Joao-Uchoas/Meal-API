package com.meal.me.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class Goals {
    private Integer dailyCalories;
    private Map<String, Object> macroNutrients;
    private Integer weight;
}
