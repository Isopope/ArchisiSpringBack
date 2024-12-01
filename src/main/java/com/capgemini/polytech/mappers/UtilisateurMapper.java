package com.capgemini.polytech.mappers;

import com.capgemini.polytech.dtos.UtilisateurDTO;
import com.capgemini.polytech.entities.UtilisateurEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;


@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UtilisateurMapper {
    UtilisateurDTO toDTO(UtilisateurEntity utilisateurEntity);
    UtilisateurEntity toEntity(UtilisateurDTO utilisateurDTO);
}
