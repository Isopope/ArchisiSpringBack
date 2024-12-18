package com.capgemini.polytech.repositories;

import com.capgemini.polytech.entities.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<UtilisateurEntity, Integer> {
    Optional<UtilisateurEntity> findByUsername(String username);
    Optional<UtilisateurEntity> findByMail(String email);
}
