package com.management.Services;

import com.management.Dto.LoginRequest;
import com.management.Dto.StudentRequest;
import com.management.Entities.Student;
import com.management.responseDto.LoginResponseDto;

public interface AuthService {

    Student signUp(StudentRequest request) throws Exception;
    LoginResponseDto login(LoginRequest request) throws Exception;
    
}
