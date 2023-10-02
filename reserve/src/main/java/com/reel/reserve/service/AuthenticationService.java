package com.reel.reserve.service;

import com.reel.reserve.dto.*;
import com.reel.reserve.exception.EmailAlreadyRegisteredException;
import com.reel.reserve.models.FrontDeskOfficer;
import com.reel.reserve.models.Role;
import com.reel.reserve.models.User;
import com.reel.reserve.repository.FrontDeskOfficerRepository;
import com.reel.reserve.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FrontDeskOfficerRepository frontDeskOfficerRepository;

    public JwtAuthenticationResponse signup(RegistrationBody body) throws EmailAlreadyRegisteredException {
        Optional<User> userOptional = userRepository.findByEmail(body.getEmail());
        if (userOptional.isPresent()) {
            throw new EmailAlreadyRegisteredException("Email :" + body.getEmail() + " Already Registered.");
        }
        User user = modelMapper.map(body, User.class);
        user.setPassword(passwordEncoder.encode(body.getPassword()));
        user.setRole(Role.ROLE_CUSTOMER);

        user = userService.saveUser(user);
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder()
                .token(jwt)
                .build();
    }
    public JwtAuthenticationResponse register(FrontDeskOfficerRegistrationBody body) throws EmailAlreadyRegisteredException {
        Optional<FrontDeskOfficer> optionalOfficer = frontDeskOfficerRepository.findByEmail(body.getEmail());
        if (optionalOfficer.isPresent()) {
            throw new EmailAlreadyRegisteredException("Email: " + body.getEmail() + " Already Registered.");
        }

        FrontDeskOfficer deskOfficer = modelMapper.map(body, FrontDeskOfficer.class);
        deskOfficer.setPassword(passwordEncoder.encode(body.getPassword()));
        deskOfficer.setRole(Role.ROLE_FRONT_DESK_OFFICER);
        deskOfficer.setJoiningDate(new Date(System.currentTimeMillis()));

        deskOfficer = userService.saveUser(deskOfficer);
        String jwt = jwtService.generateToken(deskOfficer);
        return JwtAuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    public JwtAuthenticationResponse authenticate(AuthenticationBody body) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword()));
        User user = userRepository.findByEmail(body.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Username or Password."));
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder()
                .token(jwt)
                .build();
    }
    public JwtAuthenticationResponse authenticateDeskOfficer(DeskOfficerAuthenticationBody body) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword()));
        FrontDeskOfficer deskOfficer = frontDeskOfficerRepository.findByEmail(body.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Username or Password."));
        String jwt = jwtService.generateToken(deskOfficer);
        return JwtAuthenticationResponse.builder()
                .token(jwt)
                .build();
    }
}
