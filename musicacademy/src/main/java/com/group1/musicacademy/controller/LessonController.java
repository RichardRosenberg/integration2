package com.group1.musicacademy.controller;

import com.group1.musicacademy.model.Lesson;
import com.group1.musicacademy.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true", allowedHeaders = "*")
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @GetMapping
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("This is lesson page");
    }


    @PostMapping("/add")
    public String add(@RequestBody Lesson lesson){
        lessonService.saveLesson(lesson);
        return "New lesson is added";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteLesson(@PathVariable int id) {
        lessonService.deleteById(id);
        return "Lesson with id " + id + " is deleted";
    }

    @GetMapping("/getAll")
    public List<Lesson> getAllLessons(){
        return lessonService.getAllLessons();
    }

    @PutMapping("/update/{id}")
    public String updateLesson(@PathVariable int id, @RequestBody Lesson updatedLesson) {
        Lesson existingLesson = lessonService.getLessonById(id);

        existingLesson.setTitle(updatedLesson.getTitle());
        existingLesson.setStart(updatedLesson.getStart());
        lessonService.saveLesson(existingLesson); // Save the updated lesson
        return ("Lesson with id " + id + " is updated");

    }



}
