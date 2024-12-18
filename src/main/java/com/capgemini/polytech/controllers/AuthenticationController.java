package com.capgemini.polytech.controllers;

import com.capgemini.polytech.config.LoginResponse;
import com.capgemini.polytech.dtos.LoginUtilisateurDTO;
import com.capgemini.polytech.dtos.RegisterUtilisateurDTO;
import com.capgemini.polytech.entities.UtilisateurEntity;
import com.capgemini.polytech.mappers.LoginUtilisateurMapper;
import com.capgemini.polytech.mappers.RegisterUtilisateurMapper;
import com.capgemini.polytech.services.AuthenticationService;
import com.capgemini.polytech.services.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {
    private RegisterUtilisateurMapper registerUtilisateurMapper;
    private LoginUtilisateurMapper loginUtilisateurMapper;
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService,RegisterUtilisateurMapper registerUtilisateurMapper,LoginUtilisateurMapper loginUtilisateurMapper) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.loginUtilisateurMapper=loginUtilisateurMapper;
        this.registerUtilisateurMapper=registerUtilisateurMapper;
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
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());


        return ResponseEntity.ok(loginResponse);
    }


}
