package com.reel.reserve.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/public")
    public String publicEndPoint() {
        return "This is public endpoint, accessed by anyone";
    }
    @GetMapping("/customers")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String customer() {
        return "This endpoint is secured for customers only";
    }
    @GetMapping("/admins")
    @PreAuthorize("hasRole('ADMIN')")
    public String admins() {
        return "This endpoint is secured for admins";
    }
}
