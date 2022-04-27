package com.medochemie.ordermanagement.usersservice.controller;

import com.medochemie.ordermanagement.usersservice.entity.User;
import com.medochemie.ordermanagement.usersservice.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/users")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Fetching all users from db");
        return ResponseEntity.ok().body(userService.findAllUsers());
    }


    @GetMapping("/list/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/list/user-name/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName) {
        return ResponseEntity.ok().body(userService.getUserByUserName(userName));
    }

    @PostMapping("/add-user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/users/add-user").toUriString());
        log.info("Adding {} to db", user.getFirstName());
        return ResponseEntity.created(uri).body(userService.addNewUser(user));
    }

    @PostMapping("/list/{id}/add-role")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUserName(), form.getRoleName());
        log.info(form.getUserName(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

}

@Data
class RoleToUserForm {
    private String userName;
    private String roleName;
}