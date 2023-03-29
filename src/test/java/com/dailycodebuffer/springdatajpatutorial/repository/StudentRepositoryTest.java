package com.dailycodebuffer.springdatajpatutorial.repository;

import com.dailycodebuffer.springdatajpatutorial.entity.Guardian;
import com.dailycodebuffer.springdatajpatutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //effects saved on db
//@DataJpaTest //not effect db after testing
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder().emailId("priyanshu2001@gmai.com")
                .firstName("Priyanshu")
                .lastName("Gupta")
//                .guardianName("Vaibhav Gupta")
//                .guardianEmail("gupta9811441625@gmail.com")
//                .guardianMobile("9811446791")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .name("Vaibhav Gupta")
                .email("gupta9811441625@gmail.com")
                .mobile("9811446791")
                .build();
        Student student=Student.builder().firstName("anshika").lastName("gupta").emailId("ansh@gmail.com").guardian(guardian).build();
        studentRepository.save(student);
    }
    @Test
    public void printAllStudent(){
        List<Student> studentList=studentRepository.findAll();
        System.out.println("studentList="+studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> students=studentRepository.findByFirstName("priyanshu");
        System.out.println("students = "+ students);
    }
    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("ik");
        System.out.println("students="+students);
    }


    @Test
    public void printStudentByLastName(){
        List<Student> students=studentRepository.findByLastNameNotNull();
        System.out.println("students = "+ students);
    }
    @Test
    public void printStudentByGuardianName(){
        List<Student> students=studentRepository.findByGuardianName("Vaibhav Gupta");
        System.out.println("Students ="+students);
    }

    @Test
    public void printStudentByEmail(){
        Student student=studentRepository.getStudentByEmailAddress("ansh@gmail.com");
        System.out.println("Students ="+student);
    }

    @Test
    public void printStudentNameByEmail(){
        String name = studentRepository.getStudentNameByEmailAddress("ansh@gmail.com");
        System.out.println("Students ="+name);
    }

    @Test
    public void printStudentNameByEmailNative(){
        Student name = studentRepository.getStudentByEmailAddressNative("ansh@gmail.com");
        System.out.println("Students ="+name);
    }
    @Test
    public void printStudentNameByEmailNativeNamedParam(){
        Student name = studentRepository.getStudentByEmailAddressNativeNamedParam("ansh@gmail.com");
        System.out.println("Students ="+name);
    }

    @Test
    public void updateNameByEmailTest(){
        studentRepository.updateStudentNameByEmailId("anshika2003","ansh@gmail.com");
    }
}