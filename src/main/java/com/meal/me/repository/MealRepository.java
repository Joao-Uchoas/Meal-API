package com.meal.me.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.meal.me.entity.Meal;

public interface MealRepository extends JpaRepository<Meal, Long> {
    @Query("SELECT a FROM Meal a")
    public Page<Meal> find(Pageable pageRequest);
}
