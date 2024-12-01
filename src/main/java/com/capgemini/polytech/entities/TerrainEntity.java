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
@Table(name = "terrain")
public class TerrainEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false)
    private Integer quantite;

    @Column(length = 100)
    private String description;

    @Column(nullable = false, length = 100)
    private String pointGeo;


}
