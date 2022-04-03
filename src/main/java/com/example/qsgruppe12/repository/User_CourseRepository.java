package com.example.qsgruppe12.repository;

import com.example.qsgruppe12.model.Course;
import com.example.qsgruppe12.model.User;
import com.example.qsgruppe12.model.UserInQueue;
import com.example.qsgruppe12.model.relationship.User_Course;
import com.example.qsgruppe12.model.relationshipkey.UserCourseKey;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

//QuerydslPredicateExecutor<User_Course>

@Repository
public interface User_CourseRepository extends JpaRepository<User_Course, UserCourseKey> {
    Iterable<Long> findAllByUserId(Long user_id);

    Iterable<Long> findAllByCourseId(Long course_id);

    void deleteByCourse(Course byId);
}
