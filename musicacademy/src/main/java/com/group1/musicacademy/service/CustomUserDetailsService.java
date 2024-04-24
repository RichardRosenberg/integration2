//package com.group1.musicacademy.service;
//
//import com.group1.musicacademy.entity.User;
//import com.group1.musicacademy.entity.Role;
//import com.group1.musicacademy.repository.UserRepository;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import java.util.Collection;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    private final UserRepository userRepository;
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
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
//    private UserDetails buildUserDetails(User user) {
//        Collection<? extends GrantedAuthority> authorities = mapRolesToAuthorities(user.getRoles());
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                authorities
//        );
//    }
//
//}