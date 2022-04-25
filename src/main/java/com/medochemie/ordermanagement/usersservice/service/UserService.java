package com.medochemie.ordermanagement.usersservice.service;

import com.medochemie.ordermanagement.usersservice.entity.Agent;
import com.medochemie.ordermanagement.usersservice.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    User addNewUser(User user);
    void addRoleToUser(String userName, String roleName);
    List<User> getAllUsers();
    User getUserById(String id);
    User getUserByEmail(String emailId);
    User getUserByUserName(String userName);
//    List<User> getAgentUsers(String agentId);
//    List<User> getCountryUsers(String countryCode);
    Agent getAgentInformation(String id);
}
