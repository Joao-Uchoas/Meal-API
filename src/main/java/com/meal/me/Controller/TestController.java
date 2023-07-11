package com.meal.me.Controller;

import com.meal.me.Service.TestService;
import com.meal.me.entity.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class TestController {

    public TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    // create Test by using POST Mapping
    @PostMapping("/test/create")
    public String createTest(@RequestBody Test test) throws InterruptedException, ExecutionException {
        return testService.createTest(test);
    }

    // get Tests by using GET Mapping
    @GetMapping("/test/get")
    public Test getTest(@RequestParam String documentId) throws InterruptedException, ExecutionException {
        return testService.getTest(documentId);
    }

    // Update Test by using PUT Mapping
    @PutMapping("/test/update")
    public String updateTest(@RequestBody Test test) throws InterruptedException, ExecutionException {
        return testService.updateTest(test);
    }

    // Delete Test by using DELETE Mapping
    @DeleteMapping("/test/delete")
    public String deleteTest(@RequestParam String documentId) throws InterruptedException, ExecutionException {
        return testService.deleteTest(documentId);
    }

    // Create Test Endpoint
    @GetMapping("/passei")
    public ResponseEntity<String> testGetEndpoint() {
        return ResponseEntity.ok("Test Endpoint!!");
    }

}

