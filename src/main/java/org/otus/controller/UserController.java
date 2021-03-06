package org.otus.controller;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.otus.entity.User;
import org.otus.repository.UserRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Timed(histogram = true, percentiles = {0.5, 0.95, 0.99})
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepo repo;

    @PostMapping("/user")
    public User create(@RequestBody User user) {
        return repo.create(user);
    }

    @PutMapping("/user")
    public User update(@RequestBody User user) {
        return repo.update(user);
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable int id) {
        repo.delete(id);
    }

    @GetMapping("/user/{id}")
    public User get(@PathVariable int id) {
        return repo.get(id);
    }

    @GetMapping("/user")
    public List<User> get() {
        return repo.getAll();
    }
}
