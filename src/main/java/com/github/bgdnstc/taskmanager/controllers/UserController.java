package com.github.bgdnstc.taskmanager.controllers;

import com.github.bgdnstc.taskmanager.records.User;
import com.github.bgdnstc.taskmanager.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // get all users from the database and list them
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    // retrieve one record from the users table
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    User getUserById(@PathVariable int id) {
        User user = userRepository.getUserById(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return user;
        }
    }

    // insert one record in the database
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void addUser(@RequestBody User user) {
        userRepository.insertUser(user);
    }

    // delete one record
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteUserById(@PathVariable Integer id) {
        userRepository.deleteUser(id);
    }
}
