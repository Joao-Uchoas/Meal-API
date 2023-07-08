package com.meal.me.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meal.me.Service.MealsService;
import com.meal.me.entity.Meals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MealsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MealsService mealsService;

    @InjectMocks
    private MealsController mealsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mealsController).build();
    }

    @Test
    public void testCreateMeals() throws Exception {
        Meals meals = new Meals();
        meals.setName("Test Meal");

        when(mealsService.createMeals(any(Meals.class))).thenReturn("Success");

        mockMvc.perform(post("/meals/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(meals)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMeals() throws Exception {
        String documentId = "meal1";

        when(mealsService.getMeals(documentId)).thenReturn(new Meals());

        mockMvc.perform(get("/meals/get")
                .param("documentId", documentId))
                .andExpect(status().isOk());
    }

//    @Test
//    public void testUpdateMeals() throws Exception {
//        Meals meals = new Meals();
//        meals.setName("Updated Meal");
//
//        when(mealsService.updateMeals(any(Meals.class))).thenReturn("Success");
//
//        mockMvc.perform(put("/meals/update")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(meals)))
//                .andExpect(status().isOk());
//    }

    @Test
    public void testDeleteMeals() throws Exception {
        String documentId = "meal1";

        when(mealsService.deleteMeals(documentId)).thenReturn("Success");

        mockMvc.perform(delete("/meals/delete")
                .param("documentId", documentId))
                .andExpect(status().isOk());
    }

    // Método auxiliar para converter um objeto para uma String no formato JSON.
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
