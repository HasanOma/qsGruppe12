package com.example.qsgruppe12.repository;

import com.example.qsgruppe12.model.Student_Course;
import com.example.qsgruppe12.model.relationshipkey.StudentCourseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Student_CourseRepository extends JpaRepository<Student_Course, StudentCourseKey> {
}
