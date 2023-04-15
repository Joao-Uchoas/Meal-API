package com.meal.me.controller;

import com.meal.me.dto.MealDTO;
import com.meal.me.service.MealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    @Autowired
    private MealService mealService;

    @GetMapping
    public ResponseEntity<Page<MealDTO>> getMeal(@RequestParam(value = "page",           defaultValue = "0") Integer page,
                                                    @RequestParam(value = "linesPerPage",   defaultValue = "20") Integer linesPerPage,
                                                    @RequestParam(value = "direction",      defaultValue = "ASC") String direction,
                                                    @RequestParam(value = "orderBy",        defaultValue = "id") String orderBy
    ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),orderBy);

        Page<MealDTO> list = mealService.getMeal(pageRequest);

        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<MealDTO> getMealById(@PathVariable Long id){
        MealDTO dto = mealService.getMealById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<MealDTO> insert(@RequestBody MealDTO insertDTO){
        MealDTO dto = mealService.mealInsert(insertDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        mealService.mealDelete(id);
        return ResponseEntity.noContent().build();
    }
}
