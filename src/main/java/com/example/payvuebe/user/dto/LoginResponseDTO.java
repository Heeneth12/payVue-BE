package com.example.payvuebe.user.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private String UserRole;
    private Integer userId;
    private String userName;
    private String message;


}
