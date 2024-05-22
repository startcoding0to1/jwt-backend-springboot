package com.startcoding0to1.backend.controller;

import com.startcoding0to1.backend.Dto.AuthenticationResponse;
import com.startcoding0to1.backend.Dto.RegisterRequest;
import com.startcoding0to1.backend.entity.User;
import com.startcoding0to1.backend.serviceImpl.AuthJwtServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/startcoding0to1")
public class UserController {

    @Autowired
    private AuthJwtServiceImpl userService;

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> forAdmin(){
        System.out.println("Coming+=+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        return ResponseEntity.ok("This URL is only accessible to the Admin");
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        System.out.println("Coming+=+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        return "This URL is only accessible to the User";
    }

}
