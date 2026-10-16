package com.gurjot.crudSpringBootDemo.Service;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.stereotype.Service;

import com.gurjot.crudSpringBootDemo.Entity.Student;
import com.gurjot.crudSpringBootDemo.Repository.StudentRepository;
import com.gurjot.crudSpringBootDemo.dto.CreateStudentRequestDto;
import com.gurjot.crudSpringBootDemo.dto.CreateStudentResponseDto;
import com.gurjot.crudSpringBootDemo.dto.UpdateStudentRequestDto;
import com.gurjot.crudSpringBootDemo.dto.UpdateStudentResponseDto;
import com.gurjot.crudSpringBootDemo.exception.DuplicateResourceException;
import com.gurjot.crudSpringBootDemo.exception.ResourceNotFoundException;

@Service

public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreateStudentResponseDto createStudent(CreateStudentRequestDto studentRequestDto) {

        Student student = mapToEntity(studentRequestDto);
        if (emailExists(student)) {
            throw new DuplicateResourceException("Student with email id " + student.getEmail() + " already exists");
        }
        Student studentResp = studentRepository.save(student);
        return mapToDto(studentResp);

    }

    public CreateStudentResponseDto getStudent(Long id) {
        Student studentResp = studentRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " +
                        id + "not found"));
        return mapToDto(studentResp);
    }

    public List<CreateStudentResponseDto> getAllStudent() {
        List<Student> studentList = studentRepository.findByDeletedIsFalse();
        return studentList.stream().map(this::mapToDto).toList();
    }

    public UpdateStudentResponseDto updateStudent(Long id, UpdateStudentRequestDto studentReq) {
        Student existingStudent = studentRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " +
                        id + "not found"));

        existingStudent.setName(studentReq.getName());
        existingStudent.setAge(studentReq.getAge());
        existingStudent.setRollno(studentReq.getRollno());
        existingStudent.setSubject(studentReq.getSubject());
        existingStudent.setUpdatedAt(LocalDateTime.now());
        Student savedStudent = studentRepository.save(existingStudent);
        return mapToUpdateDto(savedStudent);
    }

    public void deleteStudent(Long id) {
        Student studentToBeDeleted = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " +
                        id + "not found"));
        studentRepository.delete(studentToBeDeleted);
    }

    public void deleteStudentSoftly(Long id) {

        Student existingStudent = studentRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " +
                        id + "not found"));
        existingStudent.setDeleted(true);

        studentRepository.save(existingStudent);
    }

    private Student mapToEntity(CreateStudentRequestDto studentRequestDto) {

        Student student = new Student();

        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setRollno(studentRequestDto.getRollno());
        student.setEmail(studentRequestDto.getEmail());
        student.setSubject(studentRequestDto.getSubject());
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        student.setDeleted(false);
        return student;
    }

    private CreateStudentResponseDto mapToDto(Student student) {

        CreateStudentResponseDto studentResponseDto = new CreateStudentResponseDto();

        studentResponseDto.setId(student.getId());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setAge(student.getAge());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setRollno(student.getRollno());
        studentResponseDto.setSubject(student.getSubject());
        studentResponseDto.setMessage("Saved successfully");
        studentResponseDto.setCreatedAt(student.getCreatedAt());
        studentResponseDto.setUpdatedAt(student.getUpdatedAt());

        return studentResponseDto;
    }

    private UpdateStudentResponseDto mapToUpdateDto(Student student) {

        UpdateStudentResponseDto studentResponseDto = new UpdateStudentResponseDto();

        studentResponseDto.setId(student.getId());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setAge(student.getAge());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setRollno(student.getRollno());
        studentResponseDto.setSubject(student.getSubject());
        studentResponseDto.setMessage("Updated successfully");
        studentResponseDto.setUpdatedAt(student.getUpdatedAt());
        return studentResponseDto;
    }

    private Boolean emailExists(Student student) {
        return studentRepository.existsByEmail(student.getEmail());
    }
}
