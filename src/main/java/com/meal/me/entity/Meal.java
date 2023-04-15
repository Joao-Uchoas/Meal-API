package com.meal.me.entity;

import org.hibernate.validator.constraints.Length;
import com.meal.me.dto.MealDTO;
import jakarta.persistence.*;

@Table(name = "TB_MEAL")
@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 3, max = 50, message = "A refeicao deve ter no minino 2 caracteres e no maximo 50 caracteres.")
    private String name;

    public Meal(){}

    public Meal(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Meal(MealDTO dto) {
        this.setId(dto.getId());
        this.setName(dto.getName());
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
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
