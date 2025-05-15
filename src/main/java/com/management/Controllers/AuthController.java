package com.management.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.Dto.LoginRequest;
import com.management.Dto.StudentRequest;
import com.management.Services.AuthServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthServiceImpl authService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody StudentRequest student) throws Exception {
        return ResponseEntity.ok(authService.signUp(student));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest student) throws Exception {
        return ResponseEntity.ok(authService.login(student));
    }

}
