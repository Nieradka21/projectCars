package com.project.cars.security.jwt.model;

import lombok.Data;

@Data
public class JwtLoginInput {
    private String username;
    private String password;
}