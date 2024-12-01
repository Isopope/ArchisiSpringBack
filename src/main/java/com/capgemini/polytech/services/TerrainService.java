package com.capgemini.polytech.services;

import com.capgemini.polytech.entities.TerrainEntity;
import com.capgemini.polytech.exceptions.ApplicationException;
import com.capgemini.polytech.exceptions.ErrorCode;
import com.capgemini.polytech.repositories.TerrainRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TerrainService {
    private final TerrainRepository terrainRepository;

    public TerrainService(TerrainRepository terrainRepository) {
        this.terrainRepository = terrainRepository;
    }


    public List<TerrainEntity> getAllTerrain(){
        return terrainRepository.findAll();
    }

    public TerrainEntity getOneTerrain(Integer id){
        return terrainRepository.findById(id).orElseThrow(()->new ApplicationException(ErrorCode.RESOURCE_NOT_FOUND));
    }

    public TerrainEntity createTerrain(TerrainEntity terrainEntity){
        return terrainRepository.save(terrainEntity);
    }

    public TerrainEntity updateTerrain(Integer id, TerrainEntity terrainEntity){
        return terrainRepository.findById(id).map(
                e->terrainRepository.save(terrainEntity)).orElseThrow(()->new ApplicationException(ErrorCode.RESOURCE_NOT_FOUND)
        );
    }

    public void deleteTerrain(Integer id){
        terrainRepository.findById(id).map(
                e->{
                    terrainRepository.delete(e);
                    return e;
                }
        ).orElseThrow(()->new ApplicationException(ErrorCode.RESOURCE_NOT_FOUND));
    }

}
