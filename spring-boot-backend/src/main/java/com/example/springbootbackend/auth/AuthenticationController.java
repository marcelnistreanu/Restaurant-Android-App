package com.example.springbootbackend.auth;

import com.example.springbootbackend.config.JwtService;
import com.example.springbootbackend.dto.UserDto;
import com.example.springbootbackend.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService service;

    private final UserService userService;

    private final JwtService jwtService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        ResponseEntity<?> checkCredentialsResponse = service.checkCredentials(request);
        if (checkCredentialsResponse.getStatusCode() != HttpStatus.OK) {
            return checkCredentialsResponse;
        }
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }

    @GetMapping("/getUser")
    public ResponseEntity<UserDto> getUserInfo(@Valid @RequestHeader(value = "Authorization") String token) {
        String jwt = token.substring(7);
        String username = jwtService.extractUsername(jwt);
        UserDto userDto = userService.getUser(username);
        return ResponseEntity.ok(userDto);
    }


}
