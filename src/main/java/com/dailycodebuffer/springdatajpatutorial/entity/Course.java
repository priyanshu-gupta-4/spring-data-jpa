package com.dailycodebuffer.springdatajpatutorial.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    @Id
    @SequenceGenerator(name="course_sequence",sequenceName = "course_sequence" ,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "course_sequence")
    private long courseId;
    private String title;
    private Integer credit;

    @OneToOne(
            mappedBy = "course"             //as we have already created a foriegn key we say it is mapped by this attribute of class
    )//for bidirectional mapping
    private CourseMaterial courseMaterial;

    @ManyToOne(
        cascade = CascadeType.ALL
    )
    @JoinColumn(
            name="teacher_id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;
    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(                                      //we need a separate table to map students to course using id
            name="student_course_map",
            joinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name="student_id",
                    referencedColumnName = "studentId"
            )
    )
    private List<Student> students = new ArrayList<>();
    public List<Student> addStudent(Student student){
        if(student==null) return new ArrayList<>();
        students.add(student);
        return students;
    }
}
