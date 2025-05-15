package com.management.Services;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.management.Dto.LoginRequest;
import com.management.Dto.StudentRequest;
import com.management.Entities.Student;
import com.management.Exceptions.AccountAlreadyExistsException;
import com.management.Exceptions.SignUpFailedException;
import com.management.Exceptions.StudentNotFoundException;
import com.management.Repositories.StudentRepository;
import com.management.Utils.JwtService;
import com.management.responseDto.LoginResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final StudentRepository repository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public Student signUp(StudentRequest request) throws Exception {
        try {
            boolean isEmailExist = repository.findByEmail(request.email()).isPresent();

            if (isEmailExist) {
                throw new AccountAlreadyExistsException("Email already exist");
            }

            var student = Student.builder()
                    .firstname(request.firstname())
                    .lastname(request.lastname())
                    .email(request.email())
                    .password(passwordEncoder.encode(request.password()))
                    .createdAt(LocalDateTime.now().plusMinutes(5))
                    .updatedAt(null)
                    .build();

            var savedStudent = repository.save(student);

            return savedStudent;

        } catch (Exception e) {
            throw new SignUpFailedException("Failed to Sign up: " + e.getMessage());
        }
    }

    @Override
    public LoginResponseDto login(LoginRequest request) throws Exception {
        try {
            var student = repository.findByEmail(request.email())
                    .orElseThrow(() -> new StudentNotFoundException("Student not found"));

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()));

            var jwtToken = jwtService.generateToken(student);
            var expired = jwtService.getExpirationTime();

            return LoginResponseDto.builder()
                    .token(jwtToken)
                    .expiresIn(expired)
                    .build();
        } catch (Exception e) {

            throw new StudentNotFoundException("Student not found");
        }
    }

}
