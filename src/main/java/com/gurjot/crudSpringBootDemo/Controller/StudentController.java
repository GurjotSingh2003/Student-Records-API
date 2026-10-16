package com.gurjot.crudSpringBootDemo.Controller;

import com.gurjot.crudSpringBootDemo.Entity.Student;
import com.gurjot.crudSpringBootDemo.Service.StudentService;
import com.gurjot.crudSpringBootDemo.dto.CreateStudentRequestDto;
import com.gurjot.crudSpringBootDemo.dto.CreateStudentResponseDto;
import com.gurjot.crudSpringBootDemo.dto.UpdateStudentRequestDto;
import com.gurjot.crudSpringBootDemo.dto.UpdateStudentResponseDto;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<CreateStudentResponseDto> createStudent(
            @Valid @RequestBody CreateStudentRequestDto studentRequestDto) {
        CreateStudentResponseDto createdStudent = studentService.createStudent(studentRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDto> getStudent(@PathVariable Long id) {

        CreateStudentResponseDto studentResp = studentService.getStudent(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentResp);
    }

    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDto>> getAllStudent() {

        List<CreateStudentResponseDto> studentList = studentService.getAllStudent();
        return ResponseEntity.ok(studentList);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateStudentResponseDto> updateStudent(@PathVariable Long id,
            @RequestBody UpdateStudentRequestDto student) {

        UpdateStudentResponseDto studentResp = studentService.updateStudent(id, student);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentResp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/delete-soft/{id}")
    public ResponseEntity<String> deleteStudentSoftly(@PathVariable Long id) {

        studentService.deleteStudentSoftly(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
