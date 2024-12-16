package com.capgemini.polytech.controllers;

import com.capgemini.polytech.dtos.ReservationDTO;
import com.capgemini.polytech.entities.ReservationEntity;
import com.capgemini.polytech.mappers.ReservationMapper;
import com.capgemini.polytech.services.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    public ReservationController(ReservationService reservationService, ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        List<ReservationEntity> reservations = reservationService.getAllReservations();
        List<ReservationDTO> reservationDTOs = reservations.stream()
                .map(reservationMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reservationDTOs);
    }


    @GetMapping(value = "/{utilisateurId}/{terrainId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Integer utilisateurId, @PathVariable Integer terrainId) {
        ReservationEntity reservation = reservationService.getOneReservation(utilisateurId, terrainId);
        ReservationDTO reservationDTO = reservationMapper.toDTO(reservation);
        return ResponseEntity.ok(reservationDTO);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        ReservationEntity reservationEntity = reservationMapper.toEntity(reservationDTO);
        ReservationEntity createdReservation = reservationService.createReservation(reservationEntity);
        ReservationDTO createdReservationDTO = reservationMapper.toDTO(createdReservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReservationDTO);
    }


    @PutMapping(value = "/{utilisateurId}/{terrainId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable Integer utilisateurId, @PathVariable Integer terrainId,
                                                            @RequestBody ReservationDTO reservationDTO) {
        ReservationEntity reservationEntity = reservationMapper.toEntity(reservationDTO);
        ReservationEntity updatedReservation = reservationService.updateReservation(utilisateurId, terrainId, reservationEntity);
        ReservationDTO updatedReservationDTO = reservationMapper.toDTO(updatedReservation);
        return ResponseEntity.ok(updatedReservationDTO);
    }

    @DeleteMapping(value = "/{utilisateurId}/{terrainId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Integer utilisateurId, @PathVariable Integer terrainId) {
        reservationService.deleteReservation(utilisateurId, terrainId);
        return ResponseEntity.noContent().build();
    }
}
