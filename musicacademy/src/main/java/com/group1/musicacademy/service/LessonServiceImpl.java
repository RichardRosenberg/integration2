package com.group1.musicacademy.service;

import com.group1.musicacademy.model.Lesson;
import com.group1.musicacademy.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository lessonRepository;
    @Override
    public Lesson saveLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public void deleteById(int theId) {
        lessonRepository.deleteById(theId);
    }

    @Override
    public Lesson getLessonById(int id) {
        Optional<Lesson> result = lessonRepository.findById(id);

        Lesson theLesson = null;

        if(result.isPresent()){
            theLesson = result.get();
        }
        else{
            throw new RuntimeException("Did not find lesson");
        }

        return theLesson;

    }
}
