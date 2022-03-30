package com.example.qsgruppe12.repository;

import com.example.qsgruppe12.model.Course;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCodeAndSemester(@NotNull String code, @NotNull String semester);
    void deleteByCodeAndSemester(String code, String semester);
}