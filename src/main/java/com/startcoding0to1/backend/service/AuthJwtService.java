package com.startcoding0to1.backend.service;

import com.startcoding0to1.backend.Dto.AuthenticationRequest;
import com.startcoding0to1.backend.Dto.AuthenticationResponse;
import com.startcoding0to1.backend.Dto.RegisterRequest;

public interface AuthJwtService {
    public AuthenticationResponse registerUser(RegisterRequest registerRequest);
    public AuthenticationResponse authenticate(AuthenticationRequest request);

    void initRolesAndUsers();
}
