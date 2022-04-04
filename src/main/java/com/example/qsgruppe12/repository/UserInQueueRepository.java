package com.example.qsgruppe12.repository;

import com.example.qsgruppe12.model.UserInQueue;
import com.example.qsgruppe12.model.relationship.User_Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInQueueRepository extends JpaRepository<UserInQueue,Long>{
    List<UserInQueue> getByCourseId(Long courseId);

//    @Modifying
//    @Query("delete from UserInQueue u where u.courseId = ?1")
    void deleteByCourseId(Long courseId);


    UserInQueue getByIdAndCourseId(Long id, Long courseId);

    void deleteByCourseIdAndId(Long courseId, Long id);
}
