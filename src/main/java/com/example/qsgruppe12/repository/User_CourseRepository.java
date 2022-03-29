package com.example.qsgruppe12.repository;

import com.example.qsgruppe12.model.relationship.User_Course;
import com.example.qsgruppe12.model.relationshipkey.UserCourseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Student_CourseRepository extends JpaRepository<User_Course, UserCourseKey> {
}
