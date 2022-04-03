package com.example.qsgruppe12.repository;

import com.example.qsgruppe12.model.Queue;
import com.example.qsgruppe12.model.relationship.User_Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface QueueRepository extends JpaRepository<Queue, Long>, QuerydslPredicateExecutor<User_Course> {
    Optional<Queue> getByCourseId(Long course_id);

    void deleteByCourseId(Long courseId);

}
