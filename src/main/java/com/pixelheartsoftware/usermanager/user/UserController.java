package com.pixelheartsoftware.usermanager.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{userId}")
    public UserDto getUser(@PathVariable Integer userId) {
        return userService.getUser(userId);
    }

    @DeleteMapping("{userId}")
    public void removeUser(@PathVariable Integer userId) {
        userService.removeUser(userId);
    }

    @PostMapping
    public void addUser(@Valid @RequestBody UserDto user) {
        userService.addUser(user);
    }

    @PutMapping("{userId}")
    public void updateUser(@Valid @RequestBody UserDto user, @PathVariable Integer userId) {
        userService.updateUser(user, userId);
    }
}
