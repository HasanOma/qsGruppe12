package com.example.qsgruppe12.repository;

import com.example.qsgruppe12.model.UserInQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInQueueRepository extends JpaRepository<UserInQueue,Long> {
    List<UserInQueue> getByCourseId(Long courseId);
}
