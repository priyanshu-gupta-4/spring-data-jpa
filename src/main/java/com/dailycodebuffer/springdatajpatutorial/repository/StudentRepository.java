package com.dailycodebuffer.springdatajpatutorial.repository;

import com.dailycodebuffer.springdatajpatutorial.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    //custom methods

    //findByAttribute
    public List<Student> findByFirstName(String firstName);

    //findByAttribute by containing something
    public List<Student> findByFirstNameContaining(String firstName);

    public List<Student> findByLastNameNotNull();

    public List<Student> findByGuardianName(String guardianName);

    @Query("select s from Student s where s.emailId = ?1") //jpql ?1 for first parameter of function
    public Student getStudentByEmailAddress(String email);

    @Query("select s.firstName , s.lastName from Student s where s.emailId = ?1") //jpql ?1 for first parameter of function
    public String getStudentNameByEmailAddress(String email);

    //native query example
    @Query(value="select * from tbl_student s where s.email_address = ?1",nativeQuery = true)
    public  Student getStudentByEmailAddressNative(String email);

    //native named parameter
    @Query(value="select * from tbl_student s where s.email_address = :email",nativeQuery = true)
    public  Student getStudentByEmailAddressNativeNamedParam(@Param("email") String email);

    //update query
    @Modifying     //for queries modifying data
    @Transactional //to make query a transaction generally used at service layer not in repo
    @Query(value = "update tbl_student set first_name = ?1 where email_address = ?2",nativeQuery = true)
    int updateStudentNameByEmailId(String firstName, String emailId);
}
