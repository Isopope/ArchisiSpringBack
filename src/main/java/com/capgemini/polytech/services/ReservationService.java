package com.capgemini.polytech.services;

import com.capgemini.polytech.entities.*;
import com.capgemini.polytech.exceptions.ApplicationException;
import com.capgemini.polytech.exceptions.ErrorCode;
import com.capgemini.polytech.repositories.ReservationRepository;
import com.capgemini.polytech.repositories.TerrainRepository;
import com.capgemini.polytech.repositories.UtilisateurRepository;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final TerrainRepository terrainRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              UtilisateurRepository utilisateurRepository,
                              TerrainRepository terrainRepository) {
        this.reservationRepository = reservationRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.terrainRepository = terrainRepository;
    }

    public List<ReservationEntity> getAllReservations() {
        return reservationRepository.findAll();
    }

    public ReservationEntity getOneReservation(Integer utilisateurId, Integer terrainId) {
        ReservationId reservationId = new ReservationId(utilisateurId, terrainId);
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.RESOURCE_NOT_FOUND));
    }

    public ReservationEntity createReservation(ReservationEntity reservationEntity) {
            System.out.println(reservationEntity.getUtilisateur().getId());
        System.out.println(reservationEntity.getTerrain().getId());
        System.out.println(reservationEntity.getReservation());
        utilisateurRepository.findById(reservationEntity.getUtilisateur().getId())
               .orElseThrow(() -> new ApplicationException(ErrorCode.RESOURCE_NOT_FOUND));
        terrainRepository.findById(reservationEntity.getTerrain().getId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.RESOURCE_NOT_FOUND));
        ReservationId res=new ReservationId();
        res.setTerrainId(reservationEntity.getTerrain().getId());
        res.setUtilisateurId(reservationEntity.getUtilisateur().getId());
        reservationEntity.setId(res);
        reservationEntity.setReservation(reservationEntity.getReservation());


        return reservationRepository.save(reservationEntity);
    }

    public ReservationEntity updateReservation(Integer utilisateurId, Integer terrainId, ReservationEntity updatedReservation) {
        ReservationId reservationId = new ReservationId(utilisateurId, terrainId);

        return reservationRepository.findById(reservationId).map(existingReservation -> {
            existingReservation.setReservation(updatedReservation.getReservation());
            return reservationRepository.save(existingReservation);
        }).orElseThrow(() -> new ApplicationException(ErrorCode.RESOURCE_NOT_FOUND));
    }

    public void deleteReservation(Integer utilisateurId, Integer terrainId) {
        ReservationId reservationId = new ReservationId(utilisateurId, terrainId);
        reservationRepository.findById(reservationId).ifPresentOrElse(
                reservationRepository::delete,
                () -> { throw new ApplicationException(ErrorCode.RESOURCE_NOT_FOUND); }
        );
    }

    public List<ReservationEntity> getReservationByUtilisateur(Integer id){
        return (List<ReservationEntity>) reservationRepository.findByUtilisateurId(id);

    }
}
