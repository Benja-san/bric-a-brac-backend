package com.bricabrac.babapi.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bricabrac.babapi.entity.Role;
import com.bricabrac.babapi.entity.User;
import com.bricabrac.babapi.repository.RoleRepository;
import com.bricabrac.babapi.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService {

   private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
        UserRepository userRepository, 
        RoleRepository roleRepository,
        PasswordEncoder passwordEncoder
        ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public User register(String username, String password, String email) {
        //We want to encode the password before saving it to the database
        String encodedPassword = passwordEncoder.encode(password);
        //We want to save the user with the role USER
        Role role = roleRepository.findByAuthority("ROLE_USER").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        //We want to create a new user
        User user = new User(username, encodedPassword, email, roles);
        //We want to save the user to the database
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    
}
