package com.management.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.management.Entities.Student;
import com.management.Exceptions.StudentNotFoundException;
import com.management.Repositories.StudentRepository;
import com.management.responseDto.StudentResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final StudentMapper mapper;

    @Override
    public StudentResponseDto findStudentById(String id) {
        return repository.findById(id).map(mapper::toResponseDto).orElseThrow(() -> new StudentNotFoundException(
                String.format("No Student found with the provided ID: %s", id)));
    }

    @Override
    public List<StudentResponseDto> findAllStudents() {
        return repository.findAll().stream().map(mapper::toResponseDto).toList();
    }

    @Override
    public StudentResponseDto updateStudent(Student request, String id) {
        var student = repository.findById(id).orElseThrow(() -> new StudentNotFoundException(
                String.format("No Student found with the provided ID: %s", id)));

        // mapper.toStudent(request);

        student.setUpdatedAt(LocalDateTime.now()); 

        Student updatedStudent = repository.save(student);
        return mapper.toResponseDto(updatedStudent);
    }

    @Override
    public void deleteStudentById(String id) {
        var student = repository.findById(id).orElseThrow(() -> new StudentNotFoundException(
                String.format("No Student found with the provided ID: %s", id)));

        repository.deleteById(student.getId());
    }

}
