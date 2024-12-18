package com.capgemini.polytech.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUtilisateurDTO {
    private String nom;
    private String prenom;
    private String mail;
    private String username;
    private String password;
}
