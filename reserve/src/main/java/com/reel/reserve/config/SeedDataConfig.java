package com.reel.reserve.config;

import com.reel.reserve.models.Role;
import com.reel.reserve.models.User;
import com.reel.reserve.repository.UserRepository;
import com.reel.reserve.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User admin = User
                    .builder()
                    .name("Faizan Memon")
                    .contact("03339968110")
                    .email("faizanali7855@gmail.com")
                    .password(passwordEncoder.encode("Oneplus8"))
                    .role(Role.ROLE_ADMIN)
                    .build();
            userService.saveUser(admin);
            log.debug("created ADMIN user - {}", admin);
        }
    }
}
