//package com.group1.musicacademy.controller;
//
//import com.group1.musicacademy.entity.User;
//import com.group1.musicacademy.entity.Role;
//import com.group1.musicacademy.repository.RoleRepository;
//import com.group1.musicacademy.repository.UserRepository;
//import java.util.Collections;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class RegistrationController {
//
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public RegistrationController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @PostMapping("/register/user")
//    public String createUser(@RequestBody User user) {
//        if (userRepository.existsByUsername(user.getUsername())) {
//            return "Username already exists";
//        }
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        // Retrieve the ROLE_STUDENT from the database
//        Role role = roleRepository.findByName("ROLE_STUDENT");
//        if (role == null) {
//            // Handle the case if ROLE_STUDENT doesn't exist
//            return "Role not found";
//        }
//
//        // Assign the retrieved role to the user
//        user.setRoles(Collections.singletonList(role));
//
//        userRepository.save(user);
//        return "Registration successful! You can now login.";
//    }
//
//    @GetMapping("/check-username/{username}")
//    public boolean checkUsernameAvailability(@PathVariable String username) {
//        return !userRepository.existsByUsername(username);
//    }
//
//}
