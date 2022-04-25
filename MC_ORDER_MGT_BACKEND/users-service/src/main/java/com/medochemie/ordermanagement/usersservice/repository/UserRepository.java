package com.medochemie.ordermanagement.usersservice.repository;

import com.medochemie.ordermanagement.usersservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query(value = "{ 'emailId' : ?0 }")
    Optional<User> findByEmailId(String emailId);

    @Query(value = "{ 'userName' : ?0 }")
    Optional<User> findByUserName(String userName);

//    @Query(value = "{ 'agentId' : ?0 }")
//    List<User> findAllByAgentId(String agentId);
//
//    @Query(value = "{ 'countryCode' : ?0 }")
//    List<User> findAllByCountryCode(String countryCode);
}
