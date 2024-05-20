package dev.vishesh.vaadiyancore.controller;

import dev.vishesh.vaadiyancore.dao.AuthenticationRequest;
import dev.vishesh.vaadiyancore.dao.AuthenticationResponse;
import dev.vishesh.vaadiyancore.dao.RegisterRequest;
import dev.vishesh.vaadiyancore.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * AuthenticationController
 * This class is used to handle the authentication requests.
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final LogoutHandler logoutHandler;

    /**
     * This method is used to perform the logout operation.
     * @param authentication
     * @param request
     * @param response
     */
    @PostMapping("/logout")
    public ResponseEntity<String> performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        logoutHandler.logout(request, response, authentication);
        return ResponseEntity.ok().build();
    }

    /**
     * This method is used to register a new user.
     * @param request
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    /**
     * This method is used to authenticate the user.
     * @param request
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    /**
     * This method is used to refresh the token.
     * @param request
     * @param response
     * @throws IOException
     */
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }
}
