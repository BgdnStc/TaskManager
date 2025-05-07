package com.github.bgdnstc.taskmanager;

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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void addUser(@RequestBody User user) {
        userRepository.insertUser(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteUserById(@PathVariable Integer id) {
        userRepository.delteUser(id);
    }
}
