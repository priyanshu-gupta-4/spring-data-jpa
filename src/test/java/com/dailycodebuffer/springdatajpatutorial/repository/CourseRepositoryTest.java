package com.dailycodebuffer.springdatajpatutorial.repository;

import com.dailycodebuffer.springdatajpatutorial.entity.Course;
import com.dailycodebuffer.springdatajpatutorial.entity.Student;
import com.dailycodebuffer.springdatajpatutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.print.attribute.standard.PageRanges;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses(){
        List<Course> courses = courseRepository.findAll();
        System.out.println(courses);
    }
    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder().firstName("Priyanka").lastName("Singh").build();
        Course course = Course.builder().title("Python").credit(6).teacher(teacher).build();

        courseRepository.save(course);
    }
    @Test
    public void findAllPagination(){
        Pageable firstPagewithThreeRecords = PageRequest.of(0,3); //0th page if each has 3 records
        Pageable secondPagewithTwoRecords = PageRequest.of(1,2); //1st page if each has 2 records
        List<Course> courses = courseRepository.findAll(firstPagewithThreeRecords).getContent(); // .totalPages() pages after devision .totalElements() elements on each page
        System.out.println("courses = "+courses);
        courses = courseRepository.findAll(secondPagewithTwoRecords).getContent();
        System.out.println("courses = "+courses);
    }

    @Test
    public void findAllSorted(){
        Pageable sortByTitle = PageRequest.of(0,2,Sort.by("title"));
        Pageable sortByCreditDesc = PageRequest.of(0,2,Sort.by("credit").descending());
        Pageable sortByTitleAndCreditDesc = PageRequest.of(0,2,Sort.by("title").descending().and(Sort.by("credit")));

    }

    @Test
    public void printFindByTitleContaining(){
        Pageable firstPageTwoRecords = PageRequest.of(0,2);
        List<Course> courses = courseRepository.findByTitleContaining("d",firstPageTwoRecords).getContent();
        System.out.println("courses="+courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){
        Teacher teacher = Teacher.builder().firstName("Lizze").lastName("Morgan").build();
        Student student = Student.builder().firstName("Abhishek").lastName("singh").emailId("Abhishek@gmail.com").build();
        Course course = Course.builder().title("ai").teacher(teacher).credit(12).build();
        course.addStudent(student);
        courseRepository.save(course);
    }
}