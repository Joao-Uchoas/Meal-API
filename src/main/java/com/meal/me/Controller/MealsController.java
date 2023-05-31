package com.meal.me.Controller;

import com.meal.me.Service.MealsService;
import com.meal.me.entity.Meals;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class MealsController {

    public MealsService mealsService;

    public MealsController(MealsService mealsService) {
        this.mealsService = mealsService;
    }

    @PostMapping("/meals/create")
    public String createMeals(@RequestBody Meals meals) throws InterruptedException, ExecutionException {
        return mealsService.createMeals(meals);
    }

    @GetMapping("/meals/get")
    public Meals getMeals(@RequestParam String documentId) throws InterruptedException, ExecutionException {
        return mealsService.getMeals(documentId);
    }

    @PutMapping("/meals/update")
    public String updateMeals(@RequestBody Meals meals) throws InterruptedException, ExecutionException {
        return mealsService.updateMeals(meals);
    }

    @DeleteMapping("/meals/delete")
    public String deleteMeals(@RequestParam String documentId) throws InterruptedException, ExecutionException {
        return mealsService.deleteMeals(documentId);
    }
}
