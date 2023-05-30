package com.meal.me.entity;

import lombok.Getter;
import lombok.Setter;

import javax.crypto.Mac;

@Setter
@Getter
public class Goals {
    private Integer dailyCalories;
    private MacroNutrients macroNutrients;
    private Integer weight;
}
