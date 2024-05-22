package com.startcoding0to1.backend.serviceImpl;

import com.startcoding0to1.backend.Dto.AuthenticationRequest;
import com.startcoding0to1.backend.Dto.AuthenticationResponse;
import com.startcoding0to1.backend.Dto.RegisterRequest;
import com.startcoding0to1.backend.repository.RoleRepository;
import com.startcoding0to1.backend.repository.UserRepository;
import com.startcoding0to1.backend.entity.Role;
import com.startcoding0to1.backend.entity.User;
import com.startcoding0to1.backend.security.JwtTokenProvider;
import com.startcoding0to1.backend.service.AuthJwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthJwtServiceImpl implements AuthJwtService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse registerUser(RegisterRequest registerRequest) {
        Set<Role> roles=new HashSet<>();
        Role role=roleRepository.findById("User").get();//Here used get because it returns optional obj
        roles.add(role);
        User user=new User();
        user.setUserFirstName(registerRequest.getUserFirstName());
        user.setUserLastName(registerRequest.getUserLastName());
        user.setRoles(roles);
        user.setUserName(registerRequest.getUserLastName());
        user.setUserPassword(passwordEncoder.encode(registerRequest.getUserPassword()));

        User saved=userRepository.save(user);
        AuthenticationResponse authenticationResponse=new AuthenticationResponse();
        authenticationResponse.setUser(saved);
        String jwtToken=jwtTokenProvider.genrateJwtToken(user);
        authenticationResponse.setJwtToken(jwtToken);
        return authenticationResponse;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        //FirstStep
        //We need to validate our request (validate whether password & username is correct)
        //Verify whether user present in the database
        //Which AuthenticationProvider -> DaoAuthenticationProvider (Inject)
        //We need to authenticate using authenticationManager injecting this authenticationProvider
        //SecondStep
        //Verify whether userName and password is correct => UserNamePasswordAuthenticationToken
        //Verify whether user present in db
        //generateToken
        //Return the token
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUserName(),
                        authenticationRequest.getUserPassword()
                )
        );

        var user =userRepository.findByUserName(authenticationRequest.getUserName())
                .orElseThrow();
        String jwtToken=jwtTokenProvider.genrateJwtToken(user);

        AuthenticationResponse authenticationResponse=new AuthenticationResponse();
        authenticationResponse.setUser(user);
        authenticationResponse.setJwtToken(jwtToken);
        return authenticationResponse;
    }


    //default users created when application runs first time
    public void initRolesAndUsers(){
        Role adminRole=new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role for an Application");
        roleRepository.save(adminRole);

        Role userRole=new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created user record");
        roleRepository.save(userRole);

        User adminUser=new User();
        adminUser.setUserFirstName("Admin");
        adminUser.setUserLastName("Admin");
        adminUser.setUserName("admin123@gmail.com");
        adminUser.setUserPassword(passwordEncoder.encode("admin@123"));
        Set<Role> adminRoles=new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRoles(adminRoles);
        userRepository.save(adminUser);

//        User user=new User();
//        user.setUserFirstName("Mahammad");
//        user.setUserLastName("Khairuddin");
//        user.setUserName("user123@gmail.com");
//        user.setUserPassword("user@123");
//        Set<Role> userRoles=new HashSet<>();
//        userRoles.add(userRole);
//        user.setRoles(userRoles);
//        userRepository.save(user);

    }
}
