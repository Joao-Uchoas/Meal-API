package com.meal.me.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class Meals {
    private Long calories;
    private Map<String, Object> checkpoints;
    private String documentId;
    private Map<String, Object> ingredients;
    private Map<String, Object> macroNutrients;
    private String name;
}