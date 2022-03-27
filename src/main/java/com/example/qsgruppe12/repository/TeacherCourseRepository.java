package com.example.qsgruppe12.repository;

import com.example.qsgruppe12.model.relationship.Teacher_Course;
import com.example.qsgruppe12.model.relationshipkey.TeacherCourseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherCourseRepository extends JpaRepository<Teacher_Course, TeacherCourseKey> {
}
