package com.dailycodebuffer.springdatajpatutorial.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course") //as toString method calling a lazy attribute we excluded it
public class CourseMaterial {

    @Id
    @SequenceGenerator(name="course_material_sequence",sequenceName = "course_material_sequence" ,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "course_material_sequence")
    private long courseMaterialId;
    private String url;
    @OneToOne(
            cascade = CascadeType.ALL,      //cascading that is if an entity has no value without parent it preform same actions preformed on parent all is for all operations
            fetch = FetchType.LAZY,   //eager by def     //lazy and eager to choose if we want to load child elements or not lazy will not load eager will
            optional=false                  //weather it is optional to save courseMaterial without course
    )               //relation unidirectional if no reference in course

    @JoinColumn(
            name="course_id",                   //name of column in course material
            referencedColumnName = "courseId"  //name of attribute it maps to
    )
    private Course course;
}
