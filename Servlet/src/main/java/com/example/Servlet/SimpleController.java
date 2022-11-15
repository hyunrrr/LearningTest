package com.example.Servlet;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simple")
public class SimpleController {

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(InMemoryUserStorage.findALl());
    }

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        InMemoryUserStorage.add(user);
        return ResponseEntity.ok().build();
    }
}
