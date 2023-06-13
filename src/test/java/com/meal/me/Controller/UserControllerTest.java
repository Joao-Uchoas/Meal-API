package com.meal.me.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meal.me.Controller.UserController;
import com.meal.me.Service.UserService;
import com.meal.me.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }


    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setName("Test User");

        when(userService.createUser(any(User.class))).thenReturn("Success");

        mockMvc.perform(post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUser() throws Exception {
        String documentId = "user1";

        when(userService.getUser(documentId)).thenReturn(new User());

        mockMvc.perform(get("/users/get")
                .param("documentId", documentId))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setName("Updated User");

        when(userService.updateUser(any(User.class))).thenReturn("Success");

        mockMvc.perform(put("/users/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUser() throws Exception {
        String documentId = "user1";

        when(userService.deleteUser(documentId)).thenReturn("Success");

        mockMvc.perform(delete("/users/delete")
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
