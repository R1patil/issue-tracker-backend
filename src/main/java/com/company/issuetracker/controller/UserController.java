package com.company.issuetracker.controller;

import com.company.issuetracker.dto.UserRequestDto;
import com.company.issuetracker.entity.User;
import com.company.issuetracker.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // without using DTO
    // @PostMapping
    // public User create(@RequestBody User user) {
    // return service.save(user);
    // }

    // using DTO
    // @PostMapping
    // public User create(@RequestBody UserRequestDto dto) {
    // return service.save(dto);
    // }

    // using dto with valdation
    @PostMapping
    public User create(@Valid @RequestBody UserRequestDto dto) {
        return service.save(dto);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return service.findAll();
    }

}
