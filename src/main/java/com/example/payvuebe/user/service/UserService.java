package com.example.payvuebe.user.service;// Make sure to import your custom PasswordEncoder
import com.example.payvuebe.user.dto.LoginResponseDTO;
import com.example.payvuebe.user.entity.UserEntity;
import com.example.payvuebe.user.repository.UserRepository;
import com.example.payvuebe.utils.PasswordEncoder;
import com.example.payvuebe.utils.UserJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserJwt userJwt;

    public UserEntity register(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypting the password
        return userRepository.save(user);
    }


    public LoginResponseDTO login(String phoneNumber, String password) {
        UserEntity user = userRepository.findByNumber(phoneNumber);
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            String token = userJwt.createToken(Long.valueOf(user.getId()));
            loginResponseDTO.setToken(token);
            loginResponseDTO.setUserRole(user.getUserRole());
            loginResponseDTO.setUserId(user.getId());
            loginResponseDTO.setUserName(user.getUserName());
            loginResponseDTO.setMessage("success");
            return loginResponseDTO; // Successful login
        }
        return null; // Authentication failed
    }


}
