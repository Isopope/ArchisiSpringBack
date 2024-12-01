package com.capgemini.polytech.controllers;

import com.capgemini.polytech.dtos.UtilisateurDTO;
import com.capgemini.polytech.entities.UtilisateurEntity;
import com.capgemini.polytech.mappers.UtilisateurMapper;
import com.capgemini.polytech.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/utilisateurs")
@CrossOrigin(origins = "http://localhost:4200/")

public class UtilisateurController {
    private  UtilisateurService utilisateurService;
    private  UtilisateurMapper utilisateurMapper;
    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService, UtilisateurMapper utilisateurMapper) {
        this.utilisateurService = utilisateurService;
        this.utilisateurMapper = utilisateurMapper;
    }



    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<UtilisateurDTO>> getAllUtilisateurs() {
        List<UtilisateurDTO> utilisateurs = utilisateurService.getAllUtilisateurs()
                .stream()
                .map(utilisateurMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(utilisateurs);
    }

    // GET: Récupérer un utilisateur par son ID
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UtilisateurDTO> getUtilisateurById(@PathVariable Integer id) {
        UtilisateurEntity utilisateur = utilisateurService.getOneUtilisateur(id);
        return ResponseEntity.ok(utilisateurMapper.toDTO(utilisateur));
    }

    // POST: Créer un nouvel utilisateur
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UtilisateurDTO> createUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO) {
        UtilisateurEntity newUtilisateur = utilisateurMapper.toEntity(utilisateurDTO);
        UtilisateurEntity savedUtilisateur = utilisateurService.createUtilisateur(newUtilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(utilisateurMapper.toDTO(savedUtilisateur));
    }

    // PUT: Mettre à jour un utilisateur existant
    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UtilisateurDTO> updateUtilisateur(@PathVariable Integer id, @RequestBody UtilisateurDTO utilisateurDTO) {
        UtilisateurEntity updatedUtilisateur = utilisateurMapper.toEntity(utilisateurDTO);
        UtilisateurEntity savedUtilisateur = utilisateurService.updateUtilisateur(id, updatedUtilisateur);
        return ResponseEntity.ok(utilisateurMapper.toDTO(savedUtilisateur));
    }

    // DELETE: Supprimer un utilisateur par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Integer id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.noContent().build();
    }




}
