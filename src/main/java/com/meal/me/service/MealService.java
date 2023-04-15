package com.meal.me.service;

import com.meal.me.dto.MealDTO;
import com.meal.me.entity.Meal;
import com.meal.me.repository.MealRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;


@Service
public class MealService {

    
    @Autowired
    private MealRepository mealRepository;

    public Page<MealDTO> getMeal(PageRequest pageRequest){
        Page<Meal> list = mealRepository.find(pageRequest);
        return list.map(a -> new MealDTO(a));
    }

    public MealDTO getMealById(Long id){
        Optional<Meal> op = mealRepository.findById(id);
        Meal reg = op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Meal not found!"));
        return new MealDTO(reg);
    }

    public MealDTO mealInsert(MealDTO dto){
        Meal entity = new Meal(dto);
        entity = mealRepository.save(entity);
        return new MealDTO(entity);
    }

    public void mealDelete(Long id){
        try{
            mealRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Meal not found!");
        }
    }
}

