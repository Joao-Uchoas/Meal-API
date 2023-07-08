package com.meal.me.Controller;

import com.meal.me.Service.GoalsService;
import com.meal.me.entity.Goals;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class GoalsController {
    public GoalsService goalsService;

    public GoalsController(GoalsService goalsService) {
        this.goalsService = goalsService;
    }

    @PostMapping("/goals/create")
    public String createMeals(@RequestBody Goals goals) throws InterruptedException, ExecutionException {
        return goalsService.createGoals(goals);
    }

    @GetMapping("/goals/get")
    public Goals getMeals(@RequestParam String documentId) throws InterruptedException, ExecutionException {
        return goalsService.getGoals(documentId);
    }

    @PutMapping("/goals/update")
    public String updateMeals(@RequestBody Goals goals, @RequestParam String documentId) throws InterruptedException, ExecutionException {
        return goalsService.updateGoals(goals, documentId);
    }

    @DeleteMapping("/goals/delete")
    public String deleteMeals(@RequestParam String documentId) throws InterruptedException, ExecutionException {
        return goalsService.deleteGoals(documentId);
    }
}
