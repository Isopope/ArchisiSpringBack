package com.capgemini.polytech.controllers;

import com.capgemini.polytech.config.LoginResponse;
import com.capgemini.polytech.dtos.LoginUtilisateurDTO;
import com.capgemini.polytech.dtos.RegisterUtilisateurDTO;
import com.capgemini.polytech.entities.UtilisateurEntity;
import com.capgemini.polytech.mappers.LoginUtilisateurMapper;
import com.capgemini.polytech.mappers.RegisterUtilisateurMapper;
import com.capgemini.polytech.repositories.UtilisateurRepository;
import com.capgemini.polytech.services.AuthenticationService;
import com.capgemini.polytech.services.JwtService;
import com.capgemini.polytech.services.UtilisateurService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/authentifications")
public class AuthenticationController {
    private RegisterUtilisateurMapper registerUtilisateurMapper;
    private LoginUtilisateurMapper loginUtilisateurMapper;
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final UtilisateurRepository utilisateurRepository;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, RegisterUtilisateurMapper registerUtilisateurMapper, LoginUtilisateurMapper loginUtilisateurMapper, UtilisateurRepository utilisateurRepository) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.loginUtilisateurMapper=loginUtilisateurMapper;
        this.registerUtilisateurMapper=registerUtilisateurMapper;
        this.utilisateurRepository = utilisateurRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<UtilisateurEntity> register(@RequestBody RegisterUtilisateurDTO registerUserDto) {
        UtilisateurEntity registeredUser = authenticationService.signup(registerUtilisateurMapper.toEntity(registerUserDto));

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUtilisateurDTO loginUserDto) {
        UtilisateurEntity authenticatedUser = authenticationService.authenticate(loginUtilisateurMapper.toEntity(loginUserDto));

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        UtilisateurEntity user=utilisateurRepository.findByMail(loginUserDto.getMail()).get();

        loginResponse.setToken(jwtToken);
        loginResponse.setUserId(Long.valueOf(user.getId()));
        loginResponse.setUserName(user.getUsername());
        loginResponse.setExpiresIn(jwtService.getExpirationTime());


        return ResponseEntity.ok(loginResponse);
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            log.info("Token invalidé pour la déconnexion");
        }

        return ResponseEntity.ok("Déconnexion réussie.");
    }



}
