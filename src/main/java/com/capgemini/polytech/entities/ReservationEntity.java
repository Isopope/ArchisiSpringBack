package com.capgemini.polytech.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "reservation")
public class ReservationEntity implements Serializable {
    @EmbeddedId
    private ReservationId id;
    @ManyToOne
    @JoinColumn(name = "utilisateur_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UtilisateurEntity utilisateur;
    @ManyToOne
    @JoinColumn(name = "terrain_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TerrainEntity terrain;

    @Column(name = "reservation", nullable = false)
    private Integer reservation;


}
