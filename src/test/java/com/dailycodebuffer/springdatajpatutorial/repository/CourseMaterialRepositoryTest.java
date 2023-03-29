package com.dailycodebuffer.springdatajpatutorial.repository;

import com.dailycodebuffer.springdatajpatutorial.entity.Course;
import com.dailycodebuffer.springdatajpatutorial.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {
    @Autowired
    private CourseMaterialRepository repository;
    @Test
    public void SaveCourseMaterial(){
        Course course = Course.builder().title("Data Structures and algorithm").credit(6).build();
        //now if we dont save course in db and try to save it in course material it will give error as we are not building a relationship between them
        //so we add cascading to the class course material
        CourseMaterial courseMaterial = CourseMaterial.builder().url("www.google.com").course(course).build();
        repository.save(courseMaterial);
    }
    @Test
    public void printAllCourseMaterials(){
        List<CourseMaterial> courseMaterials = repository.findAll();
        System.out.println(courseMaterials);
    }
}