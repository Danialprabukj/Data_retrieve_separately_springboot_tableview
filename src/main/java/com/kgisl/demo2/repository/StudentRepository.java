package com.kgisl.demo2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kgisl.demo2.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Custom query method to search by day, month, and year
    @Query("SELECT s FROM Student s WHERE (:year IS NULL OR YEAR(s.dateOfBirth) = :year) AND (:month IS NULL OR MONTH(s.dateOfBirth) = :month) AND (:day IS NULL OR DAY(s.dateOfBirth) = :day)")
    List<Student> findByDayMonthYear(Integer day, Integer month, Integer year);
    //  @Query("SELECT s.college, s.gender, COUNT(s) FROM Student s GROUP BY s.college, s.gender")
    // List<Object[]> getGenderCountByCollege();
    @Query("SELECT s.college, s.gender, COUNT(s) FROM Student s GROUP BY s.college, s.gender")
    List<Object[]> getGenderCountByCollege();
}
