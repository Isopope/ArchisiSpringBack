package com.capgemini.polytech.services;

import com.capgemini.polytech.dtos.RegisterUtilisateurDTO;
import com.capgemini.polytech.entities.UtilisateurEntity;
import com.capgemini.polytech.repositories.UtilisateurRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UtilisateurRepository utilisateurRepository, AuthenticationManager authenticationManager,
                                 PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public UtilisateurEntity signup(UtilisateurEntity utilisateurEntity) {
        utilisateurEntity.setPassword(passwordEncoder.encode(utilisateurEntity.getPassword()));
        return utilisateurRepository.save(utilisateurEntity);
    }

    public UtilisateurEntity authenticate(UtilisateurEntity utilisateurEntity) {
        // Vérifier si l'utilisateur existe avant d'authentifier
        UtilisateurEntity utilisateur = utilisateurRepository.findByMail(utilisateurEntity.getMail())
                .orElseThrow(() -> new BadCredentialsException("utilisateur inexistant"));

        // Si l'utilisateur existe, vérifier le mot de passe
        boolean passwordMatch = passwordEncoder.matches(utilisateurEntity.getPassword(), utilisateur.getPassword());
        if (!passwordMatch) {
            throw new BadCredentialsException("Invalid email or password");
        }

        // Authentifier l'utilisateur
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        utilisateurEntity.getMail(),
                        utilisateurEntity.getPassword()
                )
        );

        return utilisateur;
    }
}
