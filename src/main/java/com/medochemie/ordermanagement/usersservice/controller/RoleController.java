package com.medochemie.ordermanagement.usersservice.controller;

import com.medochemie.ordermanagement.usersservice.entity.Role;
import com.medochemie.ordermanagement.usersservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/list")
    public ResponseEntity<List<Role>> getAllRoles(){
        log.info("Fetching all roles");
        return ResponseEntity.ok().body(roleService.findAllRoles());
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Role> getRole(@PathVariable String id){
        Optional<Role> role = roleService.findRoleById(id);
        log.info("Fetching {} role", role.get().getRoleName());
        return new ResponseEntity(role, HttpStatus.OK);
    }

    @PostMapping("/add-role")
    public ResponseEntity<Role> addNewRole(@RequestBody Role role){
        log.info("Adding new role to db");
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/roles/add-role").toUriString());
        return ResponseEntity.created(uri).body(roleService.addRole(role));
    }
}
