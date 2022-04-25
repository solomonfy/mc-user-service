package com.medochemie.ordermanagement.usersservice.service.impl;

import com.medochemie.ordermanagement.usersservice.entity.Role;
import com.medochemie.ordermanagement.usersservice.repository.RoleRepository;
import com.medochemie.ordermanagement.usersservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findRoleById(String id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role findRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
