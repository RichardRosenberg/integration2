package com.group1.musicacademy.controller;

import com.group1.musicacademy.model.Teacher;
import com.group1.musicacademy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

//    @GetMapping
//    public ResponseEntity<String> home(){
//        return ResponseEntity.ok("Hello, This is teacher's homePage");
//    }

    //get all teachers
    @GetMapping("/all")
    public ResponseEntity<List<Teacher>> getAllTeachers(){
        List<Teacher> teachers = teacherService.getAllTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    //Get teacher by id
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById (@PathVariable("id") Long id){
        Teacher teacher = teacherService.getTeacherById(id).orElse(null);
        if(teacher != null){
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //Add new teacher
    @PostMapping
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher){
        Teacher savedTeacher = teacherService.addTeacher(teacher);
        return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
    }

    //Update a teacher
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable("id") Long id, @RequestBody Teacher teacher ){
        Teacher updatedTeacher = teacherService.updateTeacher(id, teacher);
        if (updatedTeacher != null){
            return new ResponseEntity<>(updatedTeacher,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Delete a teacher
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable("id") Long id){
        boolean isDeleted = teacherService.deleteTeacher(id);
        if (isDeleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
