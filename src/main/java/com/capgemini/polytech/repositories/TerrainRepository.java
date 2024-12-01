package com.capgemini.polytech.repositories;

import com.capgemini.polytech.entities.TerrainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerrainRepository extends JpaRepository<TerrainEntity, Integer> {
}
