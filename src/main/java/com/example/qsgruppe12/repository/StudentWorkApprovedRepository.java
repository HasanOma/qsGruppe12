package com.example.qsgruppe12.repository;

import com.example.qsgruppe12.model.StudentWorkApproved;
import com.example.qsgruppe12.model.relationshipkey.StudentCourseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentWorkApprovedRepository extends JpaRepository<StudentWorkApproved, StudentCourseKey> {
}
