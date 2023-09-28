package com.example.webscraping.security.auth;

import com.example.webscraping.security.config.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final LogoutService logoutService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void register(@RequestBody RegisterRequest request) {
        service.register(request);
    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) {
        return service.authenticate(request);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        logoutService.logout(request, null, null);
    }
}
