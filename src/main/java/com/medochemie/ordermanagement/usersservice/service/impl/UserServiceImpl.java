package com.medochemie.ordermanagement.usersservice.service.impl;

import com.medochemie.ordermanagement.usersservice.entity.Agent;
import com.medochemie.ordermanagement.usersservice.entity.Role;
import com.medochemie.ordermanagement.usersservice.entity.User;
import com.medochemie.ordermanagement.usersservice.repository.RoleRepository;
import com.medochemie.ordermanagement.usersservice.repository.UserRepository;
import com.medochemie.ordermanagement.usersservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final static Logger logger = LoggerFactory.getLogger(Agent.class);
    private final String agentUrl = "http://MC-AGENT-SERVICE/api/v1/agents/list/";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if (user == null) {
            logger.error("User {} not found in database", userName);
            throw new UsernameNotFoundException("User not found in database");
        } else {
            logger.info("User {} not found in database", userName);
            user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        }
        return new org.springframework.security.core.userdetails.User(
                userName,
                user.getPassword(),
                authorities);
    }

    @Override
    public User addNewUser(User user) {
        logger.info("Adding {} {} to database", user.getFirstName(), user.getLastName());
        User existingUser = userRepository.findByUserName(user.getUserName().trim());
        if (existingUser == null) {
            user.setId(null);
            user.setActive(true);
            user.setCreatedOn(new Date());
            user.setUpdatedOn(new Date());
            return userRepository.save(user);
        } else {
            return new User();
        }
//        return userRepository.save(user);
    }


    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user = userRepository.findByUserName(userName);
        logger.info(user.getUserName());
        Role role = roleRepository.findByRoleName(roleName);
        logger.info(role.getRoleName());
        user.getRoles().add(role);
        logger.info(String.format(roleName, "%s has been added to %s", userName));
    }

    @Override
    public List<User> findAllUsers() {
        logger.info("Fetching all users from UserServiceImpl class");
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        logger.info("Fetching user {} with id ", id);
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByEmail(String emailId) {
        logger.info("Fetching user with emailId {}", emailId);
        return userRepository.findByEmailId(emailId).orElse(null);
    }

    @Override
    public User getUserByUserName(String userName) {
        logger.info("Fetching user {} ", userName);
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<User> getAgentUsers(String agentId) throws Exception {
        Agent agent = restTemplate.getForObject(agentUrl + agentId, Agent.class);
        if (agent != null) {
            logger.info("Fetching all users for an agent {} ", agent.getAgentName());
            return userRepository.findAllByAgentId(agentId);
        } else {
            throw new Exception("No agent found");
        }
    }

    @Override
    public List<User> getCountryUsers(String countryCode) {
        logger.info("Fetching all users for {} ", countryCode);
        Query query = new Query();

        query = (!StringUtils.isEmpty(countryCode)) ?
                query.addCriteria(Criteria.where("countryCode").is(countryCode)) : query;

        return userRepository.findAllByCountryCode(countryCode);
    }

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
