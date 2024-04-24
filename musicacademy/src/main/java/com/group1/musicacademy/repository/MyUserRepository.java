package com.group1.musicacademy.repository;

import com.group1.musicacademy.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser, Integer> {

    Optional<MyUser> findByUsername(String username);
}
