package com.capgemini.polytech.dtos;

import lombok.*;

@Getter
@Setter

public class TerrainDTO {
    private Integer id;
    private String nom;
    private Integer quantite;
    private String description;
    private String pointGeo;
}
