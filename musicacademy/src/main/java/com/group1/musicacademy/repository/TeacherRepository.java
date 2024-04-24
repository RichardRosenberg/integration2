package com.group1.musicacademy.repository;

import com.group1.musicacademy.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
