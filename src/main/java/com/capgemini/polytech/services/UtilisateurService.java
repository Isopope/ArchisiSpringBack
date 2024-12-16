package com.capgemini.polytech.services;

import com.capgemini.polytech.entities.UtilisateurEntity;
import com.capgemini.polytech.exceptions.ApplicationException;
import com.capgemini.polytech.exceptions.ErrorCode;
import com.capgemini.polytech.repositories.UtilisateurRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService  {
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<UtilisateurEntity> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public UtilisateurEntity getOneUtilisateur(Integer id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.RESOURCE_NOT_FOUND));
    }

    public UtilisateurEntity createUtilisateur(UtilisateurEntity utilisateurEntity) {
        return utilisateurRepository.save(utilisateurEntity);
    }

    public UtilisateurEntity updateUtilisateur(Integer id, UtilisateurEntity utilisateurEntity) {
        return utilisateurRepository.findById(id).map(
                existingUtilisateur -> {
                    utilisateurEntity.setId(existingUtilisateur.getId()); // Assurer la mise à jour de l'entité
                    return utilisateurRepository.save(utilisateurEntity);
                }).orElseThrow(() -> new ApplicationException(ErrorCode.RESOURCE_NOT_FOUND));
    }

    public void deleteUtilisateur(Integer id) {
        utilisateurRepository.findById(id).map(
                utilisateur -> {
                    utilisateurRepository.delete(utilisateur);
                    return utilisateur;
                }).orElseThrow(() -> new ApplicationException(ErrorCode.RESOURCE_NOT_FOUND));
    }



}
