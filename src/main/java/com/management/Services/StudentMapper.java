package com.management.Services;

import org.springframework.stereotype.Component;

import com.management.Dto.StudentRequest;
import com.management.Entities.Address;
import com.management.Entities.Student;
import com.management.responseDto.StudentResponseDto;

@Component
public class StudentMapper {

    public Student toStudent(StudentRequest request) {
        if (request == null) {
            return null;
        }

        Address address = null;
        if (request.address() != null) {
            address = Address.builder()
                    .street(request.address().getStreet())
                    .city(request.address().getCity())
                    .state(request.address().getState())
                    .zipCode(request.address().getZipCode())
                    .build();
        }

        return Student.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .password(request.password())
                .age(request.age())
                .address(address)
                .build();
    }

    

    public StudentResponseDto toResponseDto(Student student) {
        if (student == null) {
            return null;
        }

        Address address = null;
        if (student.getAddress() != null) {
            address = Address.builder()
                    .street(student.getAddress().getStreet())
                    .city(student.getAddress().getCity())
                    .state(student.getAddress().getState())
                    .zipCode(student.getAddress().getZipCode())
                    .build();
        }

        return new StudentResponseDto(
            student.getId(),
            student.getFirstname(),
            student.getLastname(),
            student.getEmail(),
            student.getPassword(),
            student.getAge(),
            address,
            student.getCreatedAt(),
            student.getUpdatedAt()
        );
                
    }

}
