package com.example.qsgruppe12.repository;

import com.example.qsgruppe12.model.Building;
import com.example.qsgruppe12.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building,Long> {
}
