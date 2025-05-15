package com.management.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.Entities.Student;
import com.management.Services.StudentServiceImpl;
import com.management.responseDto.StudentResponseDto;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class StudentController {

    private final StudentServiceImpl studentService;

    @GetMapping("/students")
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    @GetMapping("student/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable("id") String id) {
        return ResponseEntity.ok(studentService.findStudentById(id));
    }

    @DeleteMapping("student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") String id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated()
                    || !(authentication.getPrincipal() instanceof Student)) {
                return new ResponseEntity<>("Student is not authenticated", HttpStatus.UNAUTHORIZED);
            }

            Student student = (Student) authentication.getPrincipal();

            if (!student.getId().equals(id)) {
                return new ResponseEntity<>("Forbidden: Cannot delete other users", HttpStatus.FORBIDDEN);
            }

            studentService.deleteStudentById(id);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while deleting the student",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("Student/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") String id, @RequestBody Student updatedStudent) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated()
                    || !(authentication.getPrincipal() instanceof Student)) {
                return new ResponseEntity<>("User is not authenticated", HttpStatus.UNAUTHORIZED);
            }

            Student authenticatedStudent = (Student) authentication.getPrincipal();

            if (!authenticatedStudent.getId().equals(id)) {
                return new ResponseEntity<>("Forbidden: Cannot update other users", HttpStatus.FORBIDDEN);
            }

            StudentResponseDto updated = studentService.updateStudent(authenticatedStudent, id);

            return new ResponseEntity<>(updated, HttpStatus.OK);

        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while updating the student",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
