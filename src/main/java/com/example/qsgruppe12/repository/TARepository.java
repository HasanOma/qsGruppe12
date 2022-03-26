package com.example.qsgruppe12.repository;

import com.example.qsgruppe12.model.TA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TARepository extends JpaRepository<TA,Long> {

}
