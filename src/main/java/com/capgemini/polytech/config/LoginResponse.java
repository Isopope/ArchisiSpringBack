package com.capgemini.polytech.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private String userName;
    private Long userId;

    private long expiresIn;
}
