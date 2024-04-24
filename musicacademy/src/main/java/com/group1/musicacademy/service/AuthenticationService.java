package com.group1.musicacademy.service;

import com.group1.musicacademy.authentication.AuthenticationRequest;
import com.group1.musicacademy.authentication.AuthenticationResponse;
import com.group1.musicacademy.authentication.RegisterRequest;
import com.group1.musicacademy.model.MyUser;
import com.group1.musicacademy.model.Role;
import com.group1.musicacademy.repository.RoleRepository;
import com.group1.musicacademy.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = MyUser.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        String roleName = request.getRole();
        if (roleName != null) {
            Role role = roleRepository.findByName(roleName);
            if (role != null) {
                user.setRoles(Collections.singletonList(role));
            } else {
                throw new RoleNotFoundException("Requested role '" + roleName + "' does not exist");            }
        } else {
            Role defaultRole = roleRepository.findByName("ROLE_STUDENT");
            if (defaultRole != null) {
                user.setRoles(Collections.singletonList(defaultRole));
            } else {
                throw new RuntimeException("Default role 'ROLE_STUDENT' not found. Please configure default role.");
            }
        }
//        Role existingRole = roleRepository.findByName("ROLE_STUDENT");
//        if (existingRole != null) {
//            user.addRole(existingRole);
//        } else {
//            user.addRole(new Role("ROLE_STUDENT"));
//        }

//        if (user.getRoles() == null) {
//            user.setRoles(new ArrayList<>());
//        }
//        user.addRole(new Role("ROLE_STUDENT"));

//        Role role = roleRepository.findByName("ROLE_STUDENT");
//        if (role == null) {
//            // Handle the case if ROLE_STUDENT doesn't exist
//            return "Role not found";
//        }

        // Assign the retrieved role to the user
//        user.setRoles(Collections.singletonList(role));


        // or we can use a pre-existing role object like from a service or repository and then add it to the user's role list
        // Role userRole = ***
        // user.addRole(userRole);
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    // This method is same as register method without JWT token
//    public MyUser saveUser(RegisterRequest request) {
//        var user = MyUser.builder()
//                .firstname(request.getFirstname())
//                .lastname(request.getLastname())
//                .username(request.getUsername())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .build();
//
//        if (user.getRoles() == null) {
//            user.setRoles(new ArrayList<>());
//        }
//        user.addRole(new Role("USER"));
//        // Or use a pre-existing role object as explained in the register method
//
//        return repository.save(user);
//    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public Optional<MyUser> findUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public List<MyUser> findAllUsers() {
        List<MyUser> users = repository.findAll();
        return new ArrayList<>(users);
    }

    //Initialize the roles
    @PostConstruct
    public void initRoles() {
        Role studentRole = roleRepository.findByName("ROLE_STUDENT");
        if (studentRole == null) {
            studentRole = new Role();
            studentRole.setName("ROLE_STUDENT");
            roleRepository.save(studentRole);
        }

        Role teacherRole = roleRepository.findByName("ROLE_TEACHER");
        if (teacherRole == null) {
            teacherRole = new Role();
            teacherRole.setName("ROLE_TEACHER");
            roleRepository.save(teacherRole);
        }
    }

//    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> userOptional = repository.findByUsername(username);
        MyUser user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
