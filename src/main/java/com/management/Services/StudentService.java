package com.management.Services;

import java.util.List;

import com.management.Entities.Student;
import com.management.responseDto.StudentResponseDto;

public interface StudentService {

    StudentResponseDto findStudentById(String id);
    List<StudentResponseDto> findAllStudents();
    StudentResponseDto updateStudent(Student request, String id);
    void deleteStudentById(String id);
}
