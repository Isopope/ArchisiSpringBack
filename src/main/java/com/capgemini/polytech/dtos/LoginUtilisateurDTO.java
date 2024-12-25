package com.capgemini.polytech.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUtilisateurDTO {
    private int id;
    private String username;
    private String mail;
    private String password;
}
