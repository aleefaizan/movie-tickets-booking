package com.reel.reserve.service;

import com.reel.reserve.models.FrontDeskOfficer;
import com.reel.reserve.models.User;
import com.reel.reserve.repository.FrontDeskOfficerRepository;
import com.reel.reserve.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FrontDeskOfficerRepository deskOfficerRepository;
    @Autowired
    private ModelMapper modelMapper;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<User> userOptional = userRepository.findByEmail(username);
                if (userOptional.isPresent()) {
                    return userOptional.get();
                } else {
                    return deskOfficerRepository.findByEmail(username)
                            .orElseThrow(() -> new UsernameNotFoundException("User Not Found."));
                }
            }
        };
    }

    public User saveUser(User newUser) {
        if (newUser.getId() == null) {
            newUser.setCreatedAt(LocalDateTime.now());
        }
        newUser.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(newUser);
    }
    public FrontDeskOfficer saveUser(FrontDeskOfficer newOfficer) {
        if (newOfficer.getId() == null) {
            newOfficer.setCreatedAt(LocalDateTime.now());
        }
        newOfficer.setUpdatedAt(LocalDateTime.now());
        return deskOfficerRepository.save(newOfficer);
    }
}
