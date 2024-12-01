package com.capgemini.polytech.controllers;

import com.capgemini.polytech.dtos.TerrainDTO;
import com.capgemini.polytech.entities.TerrainEntity;
import com.capgemini.polytech.services.TerrainService;
import com.capgemini.polytech.mappers.TerrainMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@RestController
@RequestMapping("/api/v1/terrains")
@CrossOrigin(origins = "http://localhost:4200/")
public class TerrainController {

    private final TerrainService terrainService;
    private final TerrainMapper terrainMapper;

    public TerrainController(TerrainService terrainService, TerrainMapper terrainMapper) {
        this.terrainService = terrainService;
        this.terrainMapper = terrainMapper;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TerrainEntity> getAllTerrain() {
        return terrainService.getAllTerrain();
    }
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TerrainDTO> getOneTerrain(@PathVariable Integer id) {
        TerrainEntity terrain = terrainService.getOneTerrain(id);
        return ResponseEntity.ok(terrainMapper.toDTO(terrain));
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TerrainDTO> createTerrain(@RequestBody TerrainDTO terrainDTO) {
        TerrainEntity newTerrain = terrainMapper.toEntity(terrainDTO);
        TerrainEntity savedTerrain = terrainService.createTerrain(newTerrain);
        return ResponseEntity.status(HttpStatus.CREATED).body(terrainMapper.toDTO(savedTerrain));
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TerrainDTO> updateTerrain(@PathVariable Integer id, @RequestBody TerrainDTO terrainDTO) {
        TerrainEntity updatedTerrain = terrainMapper.toEntity(terrainDTO);
        TerrainEntity savedTerrain = terrainService.updateTerrain(id, updatedTerrain);
        return ResponseEntity.ok(terrainMapper.toDTO(savedTerrain));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTerrain(@PathVariable Integer id) {
        terrainService.deleteTerrain(id);
        return ResponseEntity.noContent().build();
    }




}
