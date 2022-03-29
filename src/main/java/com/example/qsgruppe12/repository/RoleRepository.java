package com.example.qsgruppe12.repository;

import com.example.qsgruppe12.model.Role;
import com.example.qsgruppe12.model.relationshipkey.UserCourseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, UserCourseKey> {
}
