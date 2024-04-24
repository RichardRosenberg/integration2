package com.group1.musicacademy.service;

import com.group1.musicacademy.model.Teacher;
import com.group1.musicacademy.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherRepository teacherRepository;
    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher updateTeacher(Long id, Teacher teacher) {
        Optional<Teacher> existingTeacher = teacherRepository.findById(id);
        teacher.setId(id);
        return teacherRepository.save(teacher);
    }

    @Override
    public boolean deleteTeacher(Long id) {
        Teacher teacherToBeDeleted = teacherRepository.findById(id).orElse(null);
        if (teacherToBeDeleted != null){
            teacherRepository.delete(teacherToBeDeleted);
            return true;
        }
        return false;
    }
}
