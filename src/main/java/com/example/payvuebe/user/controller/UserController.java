package com.example.payvuebe.user.controller;

import com.example.payvuebe.user.dto.LoginDTO;
import com.example.payvuebe.user.dto.LoginResponseDTO;
import com.example.payvuebe.user.entity.UserEntity;
import com.example.payvuebe.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



@RestController
@RequestMapping("/user/")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // Registration endpoint
    @PostMapping(value = "register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntity> register(@RequestBody UserEntity user) {
        UserEntity registeredUser = userService.register(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping(value = "login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginRequest) {
        LOGGER.info("Attempting login for user: {}", loginRequest.getNumber());

        LoginResponseDTO loginResponse = userService.login(loginRequest.getNumber(), loginRequest.getPassword());
        if (loginResponse != null) {
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } else {
            LoginResponseDTO failedLoginResponse = new LoginResponseDTO();
            failedLoginResponse.setMessage("Invalid username or password");
            return new ResponseEntity<>(failedLoginResponse, HttpStatus.UNAUTHORIZED);
        }
    }
}
