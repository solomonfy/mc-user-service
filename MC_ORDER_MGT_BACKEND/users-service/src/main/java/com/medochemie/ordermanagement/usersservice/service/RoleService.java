package com.medochemie.ordermanagement.usersservice.service;

import com.medochemie.ordermanagement.usersservice.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role addRole(Role role);
    Optional<Role> findRoleById(String id);
    Role findRoleByName(String roleName);
    List<Role> findAllRoles();
}
