//package com.group1.musicacademy.controller;
//
////import com.group1.musicacademy.dto.UserDto;
////import com.group1.musicacademy.entity.User;
////import com.group1.musicacademy.repository.UserRepository;
////import com.group1.musicacademy.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.ui.Model;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/login")
//public class LoginController {
//
//    private final UserService userService;
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public LoginController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userService = userService;
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @GetMapping("/form")
//    public String loginForm() {
//        return "login";
//    }
//
//    @PostMapping("/submit")
//    public String loginUser(@ModelAttribute("user") UserDto userDto, Model model, RedirectAttributes redirectAttributes) {
//        System.out.println("Received login request");
//        System.out.println("Username: " + userDto.getUsername());
//        System.out.println("Password: " + userDto.getPassword());
//
//        try {
//            Optional<User> existingUserOptional = userService.findUserByUsername(userDto.getUsername());
//            if (existingUserOptional.isPresent()) {
//                User existingUser = existingUserOptional.get();
//                if (passwordEncoder.matches(userDto.getPassword(), existingUser.getPassword())) {
//                    System.out.println("Login successful.");
//                    UserDetails userDetails = userService.loadUserByUsername(userDto.getUsername());
//                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
//                    return "login"; // Return to the login page without redirecting
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//
//        System.out.println("Login failed. Invalid username or password.");
//        model.addAttribute("error", "Invalid username or password");
//        return "login";
//    }
//}
