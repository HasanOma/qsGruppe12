package com.example.qsgruppe12.repository;

import com.example.qsgruppe12.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getByName(@NotNull String name);
}
