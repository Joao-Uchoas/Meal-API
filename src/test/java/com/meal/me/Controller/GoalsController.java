package com.meal.me.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meal.me.Service.GoalsService;
import com.meal.me.entity.Goals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GoalsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GoalsService goalsService;

    @InjectMocks
    private GoalsController goalsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(goalsController).build();
    }

    @Test
    public void testCreateGoals() throws Exception {
        Goals goals = new Goals();
        goals.setDailyCalories(2000);
        Map<String, Object> macroNutrients = new HashMap<>();
        macroNutrients.put("protein", 50);
        macroNutrients.put("carbs", 200);
        macroNutrients.put("fat", 70);
        goals.setMacroNutrients(macroNutrients);
        goals.setWeight(70);

        when(goalsService.createGoals(any(Goals.class))).thenReturn("Success");

        mockMvc.perform(post("/goals/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(goals)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetGoals() throws Exception {
        String documentId = "goal1";

        when(goalsService.getGoals(documentId)).thenReturn(new Goals());

        mockMvc.perform(get("/goals/get")
                .param("documentId", documentId))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateGoals() throws Exception {
        Goals goals = new Goals();
        goals.setDailyCalories(2500);
        Map<String, Object> macroNutrients = new HashMap<>();
        macroNutrients.put("protein", 60);
        macroNutrients.put("carbs", 250);
        macroNutrients.put("fat", 80);
        goals.setMacroNutrients(macroNutrients);
        goals.setWeight(75);

        when(goalsService.updateGoals(any(Goals.class))).thenReturn("Success");

        mockMvc.perform(put("/goals/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(goals)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteGoals() throws Exception {
        String documentId = "goal1";

        when(goalsService.deleteGoals(documentId)).thenReturn("Success");

        mockMvc.perform(delete("/goals/delete")
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
