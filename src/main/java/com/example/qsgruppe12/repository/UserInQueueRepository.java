package com.example.qsgruppe12.repository;

import com.example.qsgruppe12.model.UserInQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface UserInQueueRepository extends JpaRepository<UserInQueue,Long>, QuerydslPredicateExecutor<UserInQueue> {
    List<UserInQueue> getByCourseId(Long courseId);

//    @Modifying
//    @Query("delete from UserInQueue u where u.courseId = ?1")
    void deleteByCourseId(Long courseId);


    UserInQueue getByIdAndCourseId(Long userId, String courseId);

}
