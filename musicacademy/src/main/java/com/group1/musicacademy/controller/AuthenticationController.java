package com.group1.musicacademy.controller;

import com.group1.musicacademy.authentication.AuthenticationRequest;
import com.group1.musicacademy.authentication.AuthenticationResponse;
import com.group1.musicacademy.authentication.RegisterRequest;
import com.group1.musicacademy.model.MyUser;
import com.group1.musicacademy.repository.UserRepository;
import com.group1.musicacademy.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    private final UserRepository userRepository;

    @GetMapping("/check-username/{username}")
    public ResponseEntity<Boolean> checkUsernameAvailability(@PathVariable String username) {
        return ResponseEntity.ok(!userRepository.existsByUsername(username));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/register/teacher")
    public ResponseEntity<AuthenticationResponse> registerTeacher(@RequestBody RegisterRequest request) {
        request.setRole("ROLE_TEACHER");
        return ResponseEntity.ok(service.register(request));
    }

        @PostMapping("/register/student")
    public ResponseEntity<AuthenticationResponse> registerStudent(@RequestBody RegisterRequest request) {
        request.setRole("ROLE_STUDENT");
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = service.authenticate(request);
        return ResponseEntity.ok(response);
    }




//    @PostMapping("/refresh-token")
//    public void refreshToken(
//            HttpServletRequest request,
//            HttpServletResponse response
//    ) throws IOException {
//        service.refreshToken(request, response);
//    }
}
