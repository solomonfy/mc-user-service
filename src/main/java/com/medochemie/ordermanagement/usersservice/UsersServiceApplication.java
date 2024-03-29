package com.medochemie.ordermanagement.usersservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UsersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    CommandLineRunner run(UserService userService, RoleService roleService) {
//        return args -> {
//
//            User user1 = new User("solomon123", "solyism", "Solomon", "Yismaw",
//                    null, "sol@email.com", "sol001",
//                    null, null, new ArrayList<>(),
//                    new ArrayList<>(), true, true,
//                    null, null, null, null, null,
//                    null, null, null, null);
//
//            User user2 = new User("abebe123", "abebe", "Abebe", "Kassa",
//                    null, "abek@email.com", "abek001",
//                    null, null, new ArrayList<>(),
//                    new ArrayList<>(), true, true,
//                    null, null, null, null, null,
//                    null, null, null, null);
//
//            userService.addNewUser(user1);
//            userService.addNewUser(user2);
//
//            userService.addRoleToUser(user1.getUserName(), "ROLE_USER");
//            userService.addRoleToUser(user1.getUserName(), "ROLE_MANAGER");
//        };
//
//
//    }

}
