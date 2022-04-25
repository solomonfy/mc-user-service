package com.medochemie.ordermanagement.usersservice.repository;

import com.medochemie.ordermanagement.usersservice.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByRoleName(String roleName);
}
