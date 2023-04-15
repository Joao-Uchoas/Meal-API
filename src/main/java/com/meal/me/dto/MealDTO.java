package com.meal.me.dto;

import com.meal.me.entity.Meal;

public class MealDTO {

    private Long id;
    private String name;

    public MealDTO() {}

    public MealDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public MealDTO(Meal meal) {
        this.id = meal.getId();
        this.name = meal.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MealDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
