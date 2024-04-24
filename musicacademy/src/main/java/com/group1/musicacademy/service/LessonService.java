package com.group1.musicacademy.service;

import com.group1.musicacademy.model.Lesson;

import java.util.List;

public interface LessonService {
    public Lesson saveLesson(Lesson lesson);
    public List<Lesson> getAllLessons();
    void deleteById(int theId);

    Lesson getLessonById(int id);
}
