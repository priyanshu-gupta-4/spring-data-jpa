package com.dailycodebuffer.springdatajpatutorial.repository;

import com.dailycodebuffer.springdatajpatutorial.entity.Course;
import com.dailycodebuffer.springdatajpatutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;
    @Test
    public void saveTeacher(){
        Course course1 = Course.builder().title("DBA").credit(5).build();

        Course course2 = Course.builder().title("maths").credit(5).build();

        Course course3 = Course.builder().title("computer").credit(5).build();
        Teacher teacher = Teacher.builder().firstName("arya").lastName("bhatta")
                //.courses(List.of(course1,course2,course3))
                .build();
        teacherRepository.save(teacher);
    }
}