package com.capgemini.polytech.repositories;

import com.capgemini.polytech.entities.ReservationEntity;
import com.capgemini.polytech.entities.ReservationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, ReservationId> {

    List<ReservationEntity> findByUtilisateurId(Integer id);
}
