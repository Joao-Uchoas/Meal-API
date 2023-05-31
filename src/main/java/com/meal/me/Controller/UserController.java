package com.meal.me.Controller;

import com.meal.me.Service.UserService;
import com.meal.me.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // create user by using POST Mapping
    @PostMapping("/users/create")
    public String createUser(@RequestBody User user) throws InterruptedException, ExecutionException {
        return userService.createUser(user);
    }

    // get users by using GET Mapping
    @GetMapping("/users/get")
    public User getUser(@RequestParam String documentId) throws InterruptedException, ExecutionException {
        return userService.getUser(documentId);
    }

    // Update user by using PUT Mapping
    @PutMapping("/users/update")
    public String updateUser(@RequestBody User user) throws InterruptedException, ExecutionException {
        return userService.updateUser(user);
    }

    // Delete user by using DELETE Mapping
    @DeleteMapping("/users/delete")
    public String deleteUser(@RequestParam String documentId) throws InterruptedException, ExecutionException {
        return userService.deleteUser(documentId);
    }
}
