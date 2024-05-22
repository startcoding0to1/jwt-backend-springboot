package com.startcoding0to1.backend.controller;

import com.startcoding0to1.backend.Dto.AuthenticationRequest;
import com.startcoding0to1.backend.Dto.AuthenticationResponse;
import com.startcoding0to1.backend.Dto.RegisterRequest;
import com.startcoding0to1.backend.service.AuthJwtService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/startcoding0to1/auth")
public class AuthJWTController {

    @Autowired
    private AuthJwtService authJwtService;

    @PostConstruct //This method will execute whenever we run the application
    public void initRolesAndUsers(){
        authJwtService.initRolesAndUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authJwtService.registerUser(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authJwtService.authenticate(authenticationRequest));
    }
}
