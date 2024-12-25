package com.capgemini.polytech.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
public class ReservationId implements Serializable {
    @Column(name = "utilisateur_id")
    private Integer utilisateurId;
    @Column(name = "terrain_id")
    private Integer terrainId;

    public ReservationId(Integer utilisateurId, Integer terrainId) {
        this.utilisateurId = utilisateurId;
        this.terrainId = terrainId;
    }


}
