package com.example.qsgruppe12.repository;

import com.example.qsgruppe12.model.relationship.TA_Course;
import com.example.qsgruppe12.model.relationshipkey.TACourseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TA_CourseRepository extends JpaRepository<TA_Course, TACourseKey> {
}
