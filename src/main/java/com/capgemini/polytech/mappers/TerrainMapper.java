package com.capgemini.polytech.mappers;

import com.capgemini.polytech.dtos.TerrainDTO;
import com.capgemini.polytech.entities.TerrainEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TerrainMapper {
    TerrainDTO toDTO(TerrainEntity terrainEntity);
    TerrainEntity toEntity(TerrainDTO terrainDTO);
}
