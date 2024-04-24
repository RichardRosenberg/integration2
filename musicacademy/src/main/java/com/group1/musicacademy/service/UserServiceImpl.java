//package com.group1.musicacademy.service;
//
//
//import com.group1.musicacademy.dto.UserDto;
//import com.group1.musicacademy.entity.User;
//import com.group1.musicacademy.entity.Role;
//import com.group1.musicacademy.repository.UserRepository;
//import com.group1.musicacademy.repository.RoleRepository;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class UserServiceImpl implements UserService {
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public UserServiceImpl(UserRepository userRepository,
//                           RoleRepository roleRepository,
//                           PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void saveUser(UserDto userDto) {
//        User user = new User();
//        user.setUsername(userDto.getUsername());
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        Role role = roleRepository.findByName("ROLE_STUDENT");
//        user.setRoles(Collections.singletonList(role));
//
//        userRepository.save(user);
//    }
//
//    @Override
//    public Optional<User> findUserByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
//
//    @Override
//    public List<UserDto> findAllUsers() {
//        List<User> users = userRepository.findAll();
//        return users.stream()
//                .map(this::mapToUserDto)
//                .collect(Collectors.toList());
//    }
//
//    private UserDto mapToUserDto(User user) {
//        UserDto userDto = new UserDto();
//        userDto.setUsername(user.getUsername());
//        return userDto;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> userOptional = userRepository.findByUsername(username);
//        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
//    }
//
//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
//        return roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
//    }
//
//    @PostConstruct
//    public void initRoles() {
//        Role role = roleRepository.findByName("ROLE_STUDENT");
//        if (role == null) {
//            role = new Role();
//            role.setName("ROLE_STUDENT");
//            roleRepository.save(role);
//        }
//    }
//}
