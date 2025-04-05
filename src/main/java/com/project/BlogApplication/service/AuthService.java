package com.project.BlogApplication.service;

import com.project.BlogApplication.dto.AuthResponse;
import com.project.BlogApplication.dto.LoginRequest;
import com.project.BlogApplication.dto.RegisterRequest;
import com.project.BlogApplication.entity.User;
import com.project.BlogApplication.jwt.JwtService;
import com.project.BlogApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        String authToken = jwtService.generateToken(userDetails);
        return new AuthResponse(authToken,86000L);
    }

    public String register(RegisterRequest registerRequest) {
        Optional<User> oldUser = userRepository.findByEmail(registerRequest.getEmail());
        if(oldUser.isPresent()){
            return "user "+ registerRequest.getEmail() +" already exists !";
        }
        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return registerRequest.getEmail() + " has successfully registered !";
    }


}
