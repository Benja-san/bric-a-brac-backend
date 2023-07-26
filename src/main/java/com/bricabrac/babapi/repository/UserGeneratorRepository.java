package com.bricabrac.babapi.repository;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bricabrac.babapi.entity.Role;
import com.bricabrac.babapi.entity.User;
import com.github.javafaker.Faker;

@Component
public class UserGeneratorRepository implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final Faker faker = new Faker();

    public UserGeneratorRepository(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() > 0) {
            return;
        }

        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");
        userRole = roleRepository.save(userRole);
        adminRole = roleRepository.save(adminRole);


        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(userRole);
        adminRoles.add(adminRole);

        User user = new User(
            faker.name().username(),
            passwordEncoder.encode("password"),
            faker.internet().emailAddress(),
            userRoles
        );
        User admin = new User(
            faker.name().username(),
            passwordEncoder.encode("password"),
            faker.internet().emailAddress(),
            adminRoles
        );
        User myUser = new User(
            "Jmaniac", 
            passwordEncoder.encode("password"), 
            "jmaniac@gmail.com", 
            adminRoles
        );
        userRepository.save(user);
        userRepository.save(admin);
        userRepository.save(myUser);
    }
    
}
