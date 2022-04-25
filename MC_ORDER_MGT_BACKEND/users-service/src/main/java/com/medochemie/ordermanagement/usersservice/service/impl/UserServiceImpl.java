package com.medochemie.ordermanagement.usersservice.service.impl;

import com.medochemie.ordermanagement.usersservice.entity.Agent;
import com.medochemie.ordermanagement.usersservice.entity.Role;
import com.medochemie.ordermanagement.usersservice.entity.User;
import com.medochemie.ordermanagement.usersservice.repository.RoleRepository;
import com.medochemie.ordermanagement.usersservice.repository.UserRepository;
import com.medochemie.ordermanagement.usersservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
//@Transactional

public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(Agent.class);
    private final String agentUrl = "http://MC-AGENT-SERVICE/api/v1/agents/list/";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RestTemplate restTemplate;

    @Override
    public User addNewUser(User user) {
        logger.info("Adding {} {} to database", user.getFirstName(), user.getLastName());
        try {
            return userRepository.save(user);
        }
        catch (Exception e) {
            logger.info(e.getMessage());
            throw e;
        }

    }


    @Override
    public void addRoleToUser(String userName, String roleName) {
        Optional<User> user = userRepository.findByUserName(userName);
        logger.info(user.get().getUserName());
        Role role = roleRepository.findByRoleName(roleName);
        logger.info(role.getRoleName());
        user.get().getRoles().add(role);
        logger.info(String.format(roleName, "%s has been added to %s", userName));
    }

    @Override
    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        logger.info("Fetching user {} with id ", id);
        User user = userRepository.findById(id).get();
        return user;
    }

    @Override
    public User getUserByEmail(String emailId) {
        logger.info("Fetching user {} with emailId ", emailId);
        User user = userRepository.findByEmailId(emailId).get();
        return user;
    }

    @Override
    public User getUserByUserName(String userName) {
        logger.info("Fetching user {} ", userName);
        User user = userRepository.findByUserName(userName).get();
        return user;
    }

//    @Override
//    public List<User> getAgentUsers(String agentId) {
//        Agent agent = restTemplate.getForObject(agentUrl + agentId, Agent.class);
//        logger.info("Fetching all users for an agent {} ", agent.getAgentName());
//        return userRepository.findAllByAgentId(agentId);
//    }
//
//    @Override
//    public List<User> getCountryUsers(String countryCode) {
//        logger.info("Fetching all users for {} ", countryCode);
//        return userRepository.findAllByCountryCode(countryCode);
//    }

    @Override
    public Agent getAgentInformation(String id) {
        Optional<User> user = null;
        Agent agent = null;
        if (id != null) {
            user = userRepository.findById(id);
            try {
                agent = restTemplate.getForObject(agentUrl + user.get().getAgentId(), Agent.class);
                logger.info("Fetching agent information for a user {} ", user.get().getUserName());
                logger.info(agent.getAgentName());
                return agent;
            } catch (Exception e) {
                logger.info(e.getMessage());
                throw e;
            }
        }
        return null;
    }
}
