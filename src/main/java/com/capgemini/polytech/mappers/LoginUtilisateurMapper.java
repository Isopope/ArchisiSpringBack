package com.capgemini.polytech.mappers;

import com.capgemini.polytech.dtos.LoginUtilisateurDTO;
import com.capgemini.polytech.dtos.RegisterUtilisateurDTO;
import com.capgemini.polytech.entities.UtilisateurEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface LoginUtilisateurMapper {
    LoginUtilisateurDTO toDTO(UtilisateurEntity utilisateurEntity);
    UtilisateurEntity toEntity(LoginUtilisateurDTO utilisateurDTO);

}
