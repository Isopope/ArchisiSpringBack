package com.capgemini.polytech.mappers;

import com.capgemini.polytech.dtos.ReservationDTO;
import com.capgemini.polytech.entities.ReservationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ReservationMapper {
    @Mapping(source = "utilisateur.id", target = "utilisateurId")
    @Mapping(source = "terrain.id", target = "terrainId")
    ReservationDTO toDTO(ReservationEntity reservationEntity);
    @Mapping(source = "utilisateurId", target = "utilisateur.id")
    @Mapping(source = "terrainId", target = "terrain.id")
    ReservationEntity toEntity(ReservationDTO reservationDTO);
}
