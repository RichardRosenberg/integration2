package com.group1.musicacademy.service;

import com.group1.musicacademy.model.Teacher;
import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> getAllTeachers();
    Optional<Teacher> getTeacherById(Long id);
    Teacher addTeacher(Teacher teacher);
    Teacher updateTeacher(Long id, Teacher teacher);
    boolean deleteTeacher(Long id);
}
